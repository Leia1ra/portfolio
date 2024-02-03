function siteKeyValue() {
	const siteMap = document.getElementsByClassName("siteMap");
	Array.from(siteMap).forEach((value)=>{
		value.children.item(0).addEventListener("change", (event)=>{
			const siteValue = event.target.value;
			const linkValue = value.children.item(1);
			linkValue.name = `link[${siteValue}]`
		})
		value.children.item(2).addEventListener("click", (event)=>{
			const parent = event.target.parentNode;
			parent.remove();
		})
	})
}
function tagListValue() {
	const tagList = document.getElementsByClassName("tagList");
	Array.from(tagList).forEach((value)=>{
		value.children.item(1).addEventListener("click", (event)=>{
			const parent = event.target.parentNode;
			parent.remove();
		})
	})
}
function linkAppend() {
	const link = document.getElementById("link");
	const label = document.createElement("div");
	label.className = "siteMap deletable-area";
	label.innerHTML = `
				<input type="text" class="site">
				<input type="text" class="value" name="link[]">
				<button class="delete-button"></button>
			`;
	link.appendChild(label);

	siteKeyValue();
}
function tagAppend() {
	const tag = document.getElementById("tag");
	const label = document.createElement("div");
	label.className = "tagList deletable-area";
	label.innerHTML = `
				<input type="text" name="tag">
				<button class="delete-button"></button>
			`;
	tag.appendChild(label);

	tagListValue()
}
async function asyncAboutMe() {
	const form = document.querySelector("form[action='#aboutMe']");
	const siteMap = document.getElementsByClassName("siteMap");
	const tagList = document.getElementsByClassName("tagList");
	const imgFile = form.querySelector("#imgFile");
	Array.from(siteMap).forEach((value)=>{
		if(value.children.item(0).value==='' || value.children.item(1).value===''){
			value.remove();
		}
	})
	Array.from(tagList).forEach((value)=>{
		if(value.children.item(0).value === ''){
			value.remove();
		}
	})

	const formData = new FormData(form);
	if(imgFile.getAttribute("data-state") !== "update"){
		formData.delete("imgFile");
	}
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

function imgUpLoad(){
	let imgUpload = document.getElementById("imgFile");
	let imgPreview = document.getElementById("imgPreview");

	let file = imgUpload.files[0];
	if(file){
		let reader = new FileReader();
		reader.onload = function (e){
			console.log(e.target.result);
			imgPreview.src = e.target.result;
		};
		reader.readAsDataURL(file);
		imgUpload.setAttribute("data-state", "update")
	} else {
		imgPreview.src="";
		imgUpload.setAttribute("data-state", "default")
	}
}