"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var state;
(function (state) {
    state["SAVE"] = "save";
    state["DELETE"] = "delete";
})(state || (state = {}));
document.addEventListener("DOMContentLoaded", function () {
    const introduction = document.querySelector("#introduce-container");
    introduction.querySelectorAll("form").forEach((value) => {
        clickListener(value);
    });
});
function clickListener(value) {
    value.addEventListener('click', function (event) {
        const target = event.target;
        const id = value.querySelector("input[name='id']");
        const submit = value.querySelector("input[type='submit']");
        if (target && id) {
            if (target.className === state.SAVE) {
                asyncIntroduction(value, state.SAVE).then((response) => {
                    introductionResult(value, response.result, submit);
                });
            }
            else if (target.className === state.DELETE) {
                asyncIntroduction(value, state.DELETE).then((response) => {
                    if (response.result) {
                        value.remove();
                    }
                    else {
                        value.setAttribute("data", 'false');
                        value.querySelector(".delete").setAttribute("data", 'false');
                        submit.setAttribute("data", 'false');
                        submit.value = '삭제 실패';
                    }
                });
            }
        }
        else if (target && !id) {
            if (target.className === state.SAVE) {
                asyncIntroduction(value, state.SAVE).then((response) => {
                    introductionResult(value, response.result, submit, response.id);
                });
            }
            else if (target.className === state.DELETE) {
                value.remove();
            }
        }
    });
}
function asyncIntroduction(form, type) {
    return __awaiter(this, void 0, void 0, function* () {
        let url = '/personal/introduction?';
        {
            if (type == state.SAVE)
                url += 'save';
            else if (type == state.DELETE)
                url += 'delete';
            else
                url += 'error';
        }
        const formData = new FormData(form);
        const response = yield fetch(url, {
            method: 'POST',
            headers: {}, body: formData
        });
        return yield response.json();
    });
}
function introductionResult(form, result, message, id) {
    if (result) {
        if (id) {
            console.log(id);
            const idTag = document.createElement("input");
            idTag.type = "hidden";
            idTag.name = "id";
            idTag.value = id;
            form.appendChild(idTag);
        }
        message.value = "저장 성공";
    }
    else {
        message.setAttribute("data", 'false');
        form.setAttribute("data", 'false');
        message.value = "저장 실패";
    }
}
function introductionAppend() {
    const container = document.querySelector("#introduce-container");
    const form = document.createElement("form");
    form.addEventListener("submit", function (ev) {
        ev.preventDefault();
    });
    form.innerHTML = `
		<div><span>제목</span>
			<span class="introduction_title">
				<button type="button" class="delete"></button>
				<input type="text" name="field">
			</span> 
		</div>
		<label><span>내용</span>
			<textarea name="content"></textarea>
		</label>
		<input type="submit" class="save" value="작성 완료">
	`;
    form.action = "#newIntroduction";
    form.method = "post";
    clickListener(form);
    container.appendChild(form);
}
