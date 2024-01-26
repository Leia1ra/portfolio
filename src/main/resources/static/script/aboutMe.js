function siteKeyValue() {
	const siteMap = document.getElementsByClassName("siteMap");
	Array.from(siteMap).forEach((value)=>{
		value.children.item(2).addEventListener("change", (event)=>{
			const siteValue = event.target.value;
			const linkValue = value.children.item(1);
			linkValue.name = `link[${siteValue}]`
		})
		value.children.item(0).addEventListener("click", (event)=>{
			const parent = event.target.parentNode;
			parent.remove();
		})
	})
}
function tagListValue() {
	const tagList = document.getElementsByClassName("tagList");
	Array.from(tagList).forEach((value)=>{
		value.children.item(0).addEventListener("click", (event)=>{
			const parent = event.target.parentNode;
			parent.remove();
		})
	})
}
function linkAppend() {
	const link = document.getElementById("link");
	const label = document.createElement("div");
	label.className = "siteMap";
	label.innerHTML = `
				<button class="delete"></button>
				<input type="text" class="value" name="link[]">
				<input type="text" class="site">
			`;
	link.appendChild(label);

	siteKeyValue();
}
function tagAppend() {
	const tag = document.getElementById("tag");
	const label = document.createElement("div");
	label.className = "tagList";
	label.innerHTML = `
				<button class="delete"></button>
				<input type="text" name="tag">
			`;
	tag.appendChild(label);

	tagListValue()
}
async function asyncAboutMe() {
	console.log('ㅎㅇ');
	const form = document.querySelector("form[action='#aboutMe']");
	const siteMap = document.getElementsByClassName("siteMap");
	const tagList = document.getElementsByClassName("tagList");
	Array.from(siteMap).forEach((value)=>{
		if(value.children.item(2).value==='' || value.children.item(1).value===''){
			value.remove();
		}
	})
	Array.from(tagList).forEach((value)=>{
		if(value.children.item(1).value === ''){
			value.remove();
		}
	})

	const formData = new FormData(form);
	const response = await fetch('/personal/aboutMe',{
		method:"POST",
		headers:{
		},
		body:formData
	});
	const responseValue = await response.json();

	let message = document.getElementById("aboutMeMessage");
	if(responseValue.result){
		if(document.getElementById("aboutMeId") == null){
			const idTag = document.createElement("input");
			idTag.type = "hidden";
			idTag.name = "id";
			idTag.id = "aboutMeId"
			idTag.value = responseValue.id;
			form.appendChild(idTag);
		}
		message.value = "저장 성공"
	} else {
		message.value = "저장 실패"
	}
}