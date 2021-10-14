package edu.poly.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.service.AccountService;
import edu.poly.service.BrandService;
import edu.poly.service.CategoryService;
import edu.poly.service.ColorService;
import edu.poly.service.CommentService;
import edu.poly.service.GenderService;
import edu.poly.service.OrderDetailService;
import edu.poly.service.ProductDetailService;
import edu.poly.service.ProductService;
import edu.poly.service.SizeService;
import edu.poly.utils.ParamService;
import edu.poly.utils.SessionService;
import edu.poly.entity.Account;
import edu.poly.entity.Brand;
import edu.poly.entity.Category;
import edu.poly.entity.Color;
import edu.poly.entity.ColorPro;
import edu.poly.entity.Comment;
import edu.poly.entity.Gender;
import edu.poly.entity.OrderDetail;
import edu.poly.entity.Product;
import edu.poly.entity.ProductByColor;
import edu.poly.entity.ProductBySize;
import edu.poly.entity.ProductDetail;
import edu.poly.entity.RateAVG;
import edu.poly.entity.Size;
import edu.poly.entity.SizePro;

@Controller
public class ProductController {

	@Autowired
	ParamService paramService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productservice;

	@Autowired
	GenderService genderService;
	

	@Autowired
	AccountService accountService;

	@Autowired
	BrandService brandService;

	@Autowired
	SizeService sizeService;

	@Autowired
	ColorService colorService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	SessionService sessionService;

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	ProductDetailService productDetailService;
	
	@Autowired
	OrderDetailService orderDetailService;

	@RequestMapping("product/list")
	public String list(Model model, @RequestParam(name = "cid", required = false) Optional<String> cid,
			@RequestParam(name = "color", required = false) Long color, 
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size,
			@RequestParam(name = "min", required = false) Double min,
			@RequestParam(name = "max", required = false) Double max,
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "brand", required = false) Integer brand,
			@RequestParam(name = "gender", required = false) Integer gender) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(9);
		String categoryID = cid.orElse("");
		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("id").descending());
		Page<Product> resultPage = null;

		if (gender != null) {
			resultPage = productservice.findByGenderId(gender, pageable);
			model.addAttribute("gender", gender);
			Gender gendername = genderService.findbyGenderId(gender);
			model.addAttribute("findby", "Find by Gender: " + gendername.getName());
		} else if (brand != null) {
			resultPage = productservice.findByBrandId(brand, pageable);
			model.addAttribute("brand", brand);
			Brand brandname = brandService.findbyBrandId(brand);
			model.addAttribute("findby", "Find by Brand: " + brandname.getName());
		} else if (categoryID != "") {
			resultPage = productservice.findByCategoryId(categoryID, pageable);
			model.addAttribute("cid", categoryID);
			Category catename = categoryService.findbyCateId(categoryID);
			model.addAttribute("findby", "Find by Category: " + catename.getName());
		} else if (max != null) {
			resultPage = productservice.findByPriceContaining(min, max, pageable);
			model.addAttribute("max", max);
			model.addAttribute("min", min);
			model.addAttribute("findby", "Find by Price: to " + min + "$ from " + max + "$");
		} else if (search != null) {
			resultPage = productservice.findByKeyword("%" + search + "%", pageable);
			model.addAttribute("search", search);
			model.addAttribute("findby", "Find by Keyword: " + search);
		} else{
			resultPage = productservice.findAll(pageable);
		}
		int totalPages = resultPage.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);

			if (totalPages > 5) {
				if (end == totalPages)
					start = end - 5;
				else if (start == 1)
					end = start + 5;
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("productPage", resultPage);
		model.addAttribute("size", pageSize);		
		return "product/list";
	}
	
	
	@RequestMapping("product/listall")
	public String listall(Model model,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("sort") Optional<String> sort,
			@RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(9);
		String sortType = sort.orElse("id");
		Page<Product> resultPage = null;
		if (sortType.equals("Price Descending")) {
			Sort sortprr = Sort.by("price").descending();
			model.addAttribute("sortPr", sortType);
			Pageable pageable = PageRequest.of(currentPage - 1, pageSize, sortprr);
			resultPage = productservice.findAllTrue(pageable);
		}else if (sortType.equals("Price Ascending")) {
			Sort sortprr = Sort.by("price").ascending();
			model.addAttribute("sortPr", sortType);
			Pageable pageable = PageRequest.of(currentPage - 1, pageSize, sortprr);
			resultPage = productservice.findAllTrue(pageable);
		}else {
			Sort sortprr = Sort.by("id").ascending();
			model.addAttribute("sortPr", sortType);
			Pageable pageable = PageRequest.of(currentPage - 1, pageSize, sortprr);
			resultPage = productservice.findAllTrue(pageable);
		}
		
		int totalPages = resultPage.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);

			if (totalPages > 5) {
				if (end == totalPages)
					start = end - 5;
				else if (start == 1)
					end = start + 5;
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("productPage", resultPage);
		model.addAttribute("size", pageSize);
		
		return "product/listall";
	}
	
	

	@RequestMapping("product/listcolor")
	public String color(Model model, @RequestParam(name = "color", required = false) Integer color,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(9);
		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("product.id").descending());
		Page<ProductByColor> resultPage = null;
		resultPage = productDetailService.findByProductIDGroupByColor(color, pageable);
		model.addAttribute("color", color);
		Color colorname = colorService.findByColorId(color);
		int totalPages = resultPage.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);

			if (totalPages > 5) {
				if (end == totalPages)
					start = end - 5;
				else if (start == 1)
					end = start + 5;
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("productPage", resultPage);
		model.addAttribute("size", pageSize);
		model.addAttribute("colorname", colorname);
		return "product/listforcolor";
	}

	@RequestMapping("product/listsize")
	public String size(Model model, @RequestParam(name = "sizepro", required = false) Integer sizepro,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(9);
		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("product.id").descending());
		Page<ProductBySize> resultPage = null;

		resultPage = productDetailService.findByProductIDGroupBySize(sizepro, pageable);
		model.addAttribute("sizepro", sizepro);
		Size sizename = sizeService.findBySizeId(sizepro);

		int totalPages = resultPage.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);

			if (totalPages > 5) {
				if (end == totalPages)
					start = end - 5;
				else if (start == 1)
					end = start + 5;
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("productPage", resultPage);
		model.addAttribute("size", pageSize);
		model.addAttribute("sizename", sizename);
		return "product/listforsize";
	}


	@RequestMapping("product/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
			@RequestParam(name = "sizepro", required = false) Integer sizepro) {
		String username = request.getRemoteUser();
		if(username != null) {
//			ProductDetail item = productDetailService.findByIdandSize(id, sizepro);
//			model.addAttribute("item", item);
			Account account = accountService.findById(username);
			model.addAttribute("account", account);
			List<ColorPro> colorProlist = productDetailService.getColorByProduct(id, sizepro);
			model.addAttribute("colorProlist", colorProlist);
			Product item = productservice.findById(id);
			model.addAttribute("item", item);
			model.addAttribute("productID", id);
			List<SizePro> sizeProlist = productDetailService.getSizeByProduct(id);
			model.addAttribute("sizeProlist", sizeProlist);	
			model.addAttribute("sizepro", sizepro);
			
			Size sizeofPro = sizeService.getById(sizepro);
			model.addAttribute("sizeofPro", sizeofPro);
			
			List<ProductDetail> prodetail = productDetailService.findByProductIDandSizeID(id, sizepro);
			model.addAttribute("prodetail", prodetail);
		}else {
			List<ColorPro> colorProlist = productDetailService.getColorByProduct(id, sizepro);
			model.addAttribute("colorProlist", colorProlist);
			Product item = productservice.findById(id);
			model.addAttribute("item", item);
			model.addAttribute("productID", id);
			List<SizePro> sizeProlist = productDetailService.getSizeByProduct(id);
			model.addAttribute("sizeProlist", sizeProlist);	
			model.addAttribute("sizepro", sizepro);
			
			Size sizeofPro = sizeService.getById(sizepro);
			model.addAttribute("sizeofPro", sizeofPro);
			
			List<ProductDetail> prodetail = productDetailService.findByProductIDandSizeID(id, sizepro);
			model.addAttribute("prodetail", prodetail);
		}
		
		RateAVG rateAVG = commentService.rateAVG(id);
		if (rateAVG == null) {
			Double ratePro = 5.0;
			model.addAttribute("ratePro", ratePro);
		}else {
			Double ratePro = rateAVG.getAvg();
			model.addAttribute("ratePro", ratePro);
		}

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(3);
		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("id").descending());
		Page<Comment> resultPage = null;
		resultPage = commentService.findByProductId(id, pageable);

		int totalPages = resultPage.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);

			if (totalPages > 5) {
				if (end == totalPages)
					start = end - 5;
				else if (start == 1)
					end = start + 5;
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("cmtlist", resultPage);
		model.addAttribute("size", pageSize);
		
		
		return "product/detail";
	}
	
	
	@PostMapping("/product/filter")
	public String filter(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		String cateid = paramService.getString("cid", "");
		String brandname = paramService.getString("brand", "");
		String sizeproname = paramService.getString("sizepro", "");
		String gendername = paramService.getString("gender", "");
		String colorname = paramService.getString("color", "");
		String min = paramService.getString("min", "");
		String max = paramService.getString("max", "");
		
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(9999);
		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("product.id").descending());
		Page<ProductDetail> resultPage = null;

		resultPage = productDetailService.filterProductDetail("%" + cateid, "%" + brandname, "%" + sizeproname, gendername + "%", "%" + colorname, Double.parseDouble(min), Double.parseDouble(max), pageable);

		int totalPages = resultPage.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);

			if (totalPages > 5) {
				if (end == totalPages)
					start = end - 5;
				else if (start == 1)
					end = start + 5;
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("productPage", resultPage);
		model.addAttribute("size", pageSize);
		model.addAttribute("size", pageSize);		
		return "product/filter";
	}
	
	
	@RequestMapping("/productdetail/update/{id}")
	public String productdetaulupdate(Model model, @PathVariable("id") Long id) {
		List<OrderDetail> listOrDe = orderDetailService.findByOrder(id);
		for(int i=0; i<listOrDe.size(); i++) {
			int OrDeQuan = listOrDe.get(i).getQuantity();
			Long ProDeId = listOrDe.get(i).getProductDetail().getId();
			ProductDetail ProDe = productDetailService.findbyId(ProDeId);
			ProductDetail entity = new ProductDetail();
			//copy từ dto qua entity
			BeanUtils.copyProperties(ProDe, entity);
			entity.setQuantity(entity.getQuantity()-OrDeQuan);
			productDetailService.save(entity);
		}
		return "redirect:/order/success/{id}";
	}
}
