package edu.poly.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

import edu.poly.service.BrandService;
import edu.poly.service.CategoryService;
import edu.poly.service.ColorService;
import edu.poly.service.GenderService;
import edu.poly.service.ProductDetailService;
import edu.poly.service.ProductService;
import edu.poly.service.SizeService;
import edu.poly.utils.ParamService;
import edu.poly.utils.SessionService;
import edu.poly.entity.Brand;
import edu.poly.entity.Category;
import edu.poly.entity.Color;
import edu.poly.entity.ColorPro;
import edu.poly.entity.Gender;
import edu.poly.entity.Product;
import edu.poly.entity.ProductDetail;
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
	BrandService brandService;

	@Autowired
	SizeService sizeService;

	@Autowired
	ColorService colorService;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	ProductDetailService productDetailService;

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
		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("name"));
		Page<Product> resultPage = null;

		if (gender != null) {
			resultPage = productservice.findByGenderId(gender, pageable);
			model.addAttribute("gender", gender);
		} else if (brand != null) {
			resultPage = productservice.findByBrandId(brand, pageable);
			model.addAttribute("brand", brand);
		} else if (categoryID != "") {
			resultPage = productservice.findByCategoryId(categoryID, pageable);
			model.addAttribute("cid", categoryID);
		} else if (max != null) {
			resultPage = productservice.findByPriceContaining(min, max, pageable);
			model.addAttribute("max", max);
			model.addAttribute("min", min);
		} else if (search != null) {
			resultPage = productservice.findByKeyword("%" + search + "%", pageable);
			model.addAttribute("search", search);
		} else{
//			List<Product> list = productservice.findAll();
			resultPage = productservice.findAll(pageable);
//			model.addAttribute("items", list);
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

		List<Gender> genderlist = genderService.findAll();
		model.addAttribute("genderlist", genderlist);

		List<Brand> brands = brandService.findAll();
		model.addAttribute("brands", brands);

		List<Size> sizes = sizeService.findAll();
		model.addAttribute("sizes", sizes);

		List<Color> colors = colorService.findAll();
		model.addAttribute("colors", colors);
		
		List<Category> cate = categoryService.findAll();
		model.addAttribute("cate", cate);
		return "product/list";
	}
	
	
	@RequestMapping("product/listall")
	public String listall(Model model,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(9);
		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("name"));
		Page<Product> resultPage = null;
		resultPage = productservice.findAll(pageable);
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

		List<Gender> genderlist = genderService.findAll();
		model.addAttribute("genderlist", genderlist);

		List<Brand> brands = brandService.findAll();
		model.addAttribute("brands", brands);

		List<Size> sizes = sizeService.findAll();
		model.addAttribute("sizes", sizes);

		List<Color> colors = colorService.findAll();
		model.addAttribute("colors", colors);
		
		List<Category> cate = categoryService.findAll();
		model.addAttribute("cate", cate);
		return "product/listall";
	}
	
	

	@RequestMapping("product/listcolor")
	public String color(Model model, @RequestParam(name = "color", required = false) Integer color,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(9);
		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("id"));
		Page<ProductDetail> resultPage = null;

		resultPage = productDetailService.findByColor(color, pageable);
		model.addAttribute("color", color);

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

		List<Gender> genderlist = genderService.findAll();
		model.addAttribute("genderlist", genderlist);
		
		List<Brand> brands = brandService.findAll();
		model.addAttribute("brands", brands);

		List<Size> sizes = sizeService.findAll();
		model.addAttribute("sizes", sizes);

		List<Category> cate = categoryService.findAll();
		model.addAttribute("cate", cate);
		
		List<Color> colors = colorService.findAll();
		model.addAttribute("colors", colors);
		return "product/listforcolor";
	}

	@RequestMapping("product/listsize")
	public String size(Model model, @RequestParam(name = "sizepro", required = false) Integer sizepro,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(9);
		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("id"));
		Page<ProductDetail> resultPage = null;

		resultPage = productDetailService.findBySize(sizepro, pageable);
		model.addAttribute("sizepro", sizepro);

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

		List<Gender> genderlist = genderService.findAll();
		model.addAttribute("genderlist", genderlist);
		
		List<Brand> brands = brandService.findAll();
		model.addAttribute("brands", brands);

		List<Category> cate = categoryService.findAll();
		model.addAttribute("cate", cate);
		
		List<Size> sizes = sizeService.findAll();
		model.addAttribute("sizes", sizes);

		List<Color> colors = colorService.findAll();
		model.addAttribute("colors", colors);
		return "product/listforsize";
	}


	@RequestMapping("product/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id,
			@RequestParam(name = "sizepro", required = false) Integer sizepro) {
		
		if(sizepro != null) {
//			ProductDetail item = productDetailService.findByIdandSize(id, sizepro);
//			model.addAttribute("item", item);
			List<ColorPro> colorProlist = productDetailService.getColorByProduct(id, sizepro);
			model.addAttribute("colorProlist", colorProlist);
			Product item = productservice.findById(id);
			model.addAttribute("item", item);
			model.addAttribute("productID", id);
			List<SizePro> sizeProlist = productDetailService.getSizeByProduct(id);
			model.addAttribute("sizeProlist", sizeProlist);	
			model.addAttribute("sizepro", sizepro);
			
			List<ProductDetail> prodetail = productDetailService.findByProductIDandSizeID(id, sizepro);
			model.addAttribute("prodetail", prodetail);
		}else {
			Product item = productservice.findById(id);
			model.addAttribute("item", item);
			model.addAttribute("productID", id);
			List<SizePro> sizeProlist = productDetailService.getSizeByProduct(id);
			model.addAttribute("sizeProlist", sizeProlist);
			List<ColorPro> colorProlist = productDetailService.getColorByProduct(id);
			model.addAttribute("colorProlist", colorProlist);
		}
		List<Gender> genderlist = genderService.findAll();
		model.addAttribute("genderlist", genderlist);
		
		List<Brand> brands = brandService.findAll();
		model.addAttribute("brands", brands);
		
		List<Category> cate = categoryService.findAll();
		model.addAttribute("cate", cate);

		List<Size> sizes = sizeService.findAll();
		model.addAttribute("sizes", sizes);

		List<Color> colors = colorService.findAll();
		model.addAttribute("colors", colors);
		return "product/detail";
	}
	
	
	@PostMapping("/product/filter")
	public String filter(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		String cateid = paramService.getString("cid", "");
		String brandid = paramService.getString("brand", "");
		String sizeproid = paramService.getString("sizepro", "");
		String genderid = paramService.getString("gender", "");
		String colorid = paramService.getString("color", "");
		String min = paramService.getString("min", "");
		String max = paramService.getString("max", "");
		System.out.println(cateid + brandid + sizeproid + genderid + colorid + min + max);
		
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(9);
		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("id"));
		Page<ProductDetail> resultPage = null;

		resultPage = productDetailService.filterAllProduct("%" + cateid, brandid, sizeproid, genderid, colorid, Double.parseDouble(min), Double.parseDouble(max), pageable);

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

		List<Gender> genderlist = genderService.findAll();
		model.addAttribute("genderlist", genderlist);
		
		List<Brand> brands = brandService.findAll();
		model.addAttribute("brands", brands);
		
		List<Category> cate = categoryService.findAll();
		model.addAttribute("cate", cate);

		List<Size> sizes = sizeService.findAll();
		model.addAttribute("sizes", sizes);

		List<Color> colors = colorService.findAll();
		model.addAttribute("colors", colors);
		
		return "product/filter";
	}
}
