document.addEventListener("DOMContentLoaded", ()=>{
	siteKeyValue()
	tagListValue()
	Array.from(document.getElementsByTagName("form")).forEach((value)=>{
		value.addEventListener("submit", (event)=>{
			event.preventDefault();
		})
	})
})