document.addEventListener("DOMContentLoaded", ()=>{
	siteKeyValue();
	tagListValue();
	Array.from(document.getElementsByTagName("form")).forEach((value)=>{
		value.addEventListener("submit", (event)=>{
			event.preventDefault();
		})
	})

	document.querySelectorAll("section").forEach((value, key) => {
		value.querySelector('h2').addEventListener('click',(event)=>{
			const parent = event.currentTarget;

			if(parent){
				const toggle = parent.querySelector(".menu-toggle");
				if(value.getAttribute("data-state")==="shrink"){
					value.setAttribute("data-state", "expand")
				} else {
					value.setAttribute("data-state", "shrink")
				}
			}
		})

	});
	document.querySelector("#imgContainer").addEventListener('click', function (e){
		e.currentTarget.querySelector("input[type='file']").click();
	})

	document.querySelectorAll("textarea").forEach((value)=>{
		value.addEventListener("click", (e) => {
			e.target.style.height = 'auto';
			e.target.style.height = e.target.scrollHeight + 10 + 'px';
		})
	})
})

function textAreaAutoSizing(value) {
	console.log(value.scrollHeight);
	value.style.height = 'auto';
	value.style.height = value.scrollHeight + 10 + 'px';
}
