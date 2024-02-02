document.addEventListener("DOMContentLoaded", function () {
	const introduction = document.querySelector("#introduce-container") as HTMLDivElement;
	let currentItemIndex : number;
	let currentItem : HTMLElement;

	introduction.querySelectorAll("form").forEach((value) => {
		introductionProcess(value);
	})
})
function introductionProcess(value:HTMLFormElement) {
	value.addEventListener('click', function (event) {
		const target = event.target as (HTMLInputElement | HTMLButtonElement);
		const id = value.querySelector("input[name='id']") as (HTMLInputElement | null);
		const submit = value.querySelector("input[type='submit']") as HTMLInputElement;

		if(target && id) {
			if(target.getAttribute("data-state") === state.SAVE){
				asyncIntroduction(value, state.SAVE).then((response)=>{
					introductionResult(value, response.result, submit);
				});
			} else if(target.getAttribute("data-state") === state.DELETE){
				asyncIntroduction(value, state.DELETE).then((response)=>{
					if(response.result){
						value.remove();
					} else {
						value.setAttribute("data", 'false');
						(value.querySelector(".delete-button") as HTMLButtonElement).setAttribute("data", 'false');
						submit.setAttribute("data", 'false');
						submit.value = '삭제 실패';
					}
				});
			}
		} else if(target && !id){
			if(target.getAttribute("data-state") === state.SAVE){
				asyncIntroduction(value, state.SAVE).then((response)=>{
					introductionResult(value, response.result, submit, response.id);
				});
			} else if(target.getAttribute("data-state") === state.DELETE){
				value.remove();
			}
		}
	})
}
async function asyncIntroduction(form: HTMLFormElement, type:state){
	let url:string ='/personal/introduction?';
	{	if (type == state.SAVE) url += 'save';
		else if(type == state.DELETE) url += 'delete';
		else url += 'error'
	}
	const formData = new FormData(form);
	const response = await fetch(url, {
		method:'POST',
		headers:{
		}, body:formData
	})
	return await response.json();
}
function introductionResult(form:HTMLFormElement,result:boolean, message:HTMLInputElement, id?:string) {
	if(result){
		if(id){
			console.log(id);
			const idTag = document.createElement("input");
			idTag.type = "hidden";
			idTag.name = "id";
			idTag.value = id;
			form.appendChild(idTag);
		}
		message.value = "저장 성공";
	} else {
		message.setAttribute("data", 'false');
		form.setAttribute("data", 'false');
		message.value = "저장 실패";
	}
}
function introductionAppend() {
	const container = document.querySelector("#introduce-container")as HTMLDivElement;
	const form = document.createElement("form");
	form.addEventListener("submit", function (ev) {
		ev.preventDefault();
	})
	form.innerHTML = `
		<div><span>제목</span>
			<span class="introduction_title deletable-area">
				<input type="number" name="no" style="width: 40px">
				<input type="text" name="field">
				<button type="button" class="delete-button" data-state="delete"></button>
			</span> 
		</div>
		<label><span>내용</span>
			<textarea name="content" oninput="textAreaAutoSizing(this)"></textarea>
		</label>
		<input type="submit" class="save-button" data-state="save" value="작성 완료">
	`;
	form.action = "#newIntroduction";
	form.method = "post";
	introductionProcess(form);

	container.appendChild(form);
}