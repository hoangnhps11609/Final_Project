
function revenue(id, monthLabels=[], revenueLabels=[]){
	let colors = []
	for (let i = 0; i< revenueLabels.length; i++)
		colors.push(generateColor())
		
	const data = {
		labels: monthLabels,
		datasets: [{
			label: 'Thong ke',
			data: revenueLabels,
			backgroundColor: colors,
			borderColor: colors,
			hoverOffset: 4
		}]
	};
	
	const config ={
		type: 'bar',
		data: data,
	};
	
	let ctx = document.getElementById(id).getContext("2d")
	new Chart(ctx, config)
}