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
document.addEventListener("DOMContentLoaded", (e) => {
    const growth = document.querySelector("#award-container");
    const append = document.querySelector('#award-append');
    growth.querySelectorAll('form').forEach((value) => {
        awardProcess(value);
    });
    /*확장 버튼*/
    append.addEventListener('click', (e) => {
        const form = document.createElement('form');
        form.addEventListener('submit', (e) => {
            e.preventDefault();
        });
        form.method = 'post';
        form.action = '#newAward';
        form.innerHTML = `
			<div class="award-details deletable-area">
				<label>날짜
					<input type="date" name="date">
				</label>
				<label>제목
					<input type="text" name="title">
				</label>
				<label>기관
					<input type="text" name="location">
				</label>
				<label>등수
					<input type="text" name="rank">
				</label>
				<button type="button" class="delete-button" data-state="delete"></button>
			</div>
			<input type="submit" class="save-button" data-state="save" value="작성 완료">
		`;
        awardProcess(form);
        growth.appendChild(form);
    });
});
function asyncAward(form, type) {
    return __awaiter(this, void 0, void 0, function* () {
        let url = '/personal/award?';
        if (type == state.SAVE)
            url += 'save';
        else if (type == state.DELETE)
            url += 'delete';
        else
            url += 'error';
        const formData = new FormData(form);
        const response = yield fetch(url, {
            method: 'POST',
            headers: {}, body: formData
        });
        return yield response.json();
    });
}
function awardProcess(value) {
    value.addEventListener('click', (e) => {
        console.log(e.currentTarget);
        console.log(e.target);
        const target = e.target;
        const id = value.querySelector("input[name='id']");
        const submit = value.querySelector("input[type='submit']");
        if (target && id) {
            if (target.getAttribute('data-state') === state.SAVE) {
                asyncAward(value, state.SAVE).then((response) => {
                    awardResult(value, response.result, submit);
                });
            }
            else if (target.getAttribute('data-state') === state.DELETE) {
                asyncAward(value, state.DELETE).then((response) => {
                    if (response.result) {
                        value.remove();
                    }
                    else {
                        value.setAttribute("data", 'false');
                        value.querySelector(".delete-button").setAttribute("data", 'false');
                        submit.setAttribute("data", 'false');
                        submit.value = '삭제 실패';
                    }
                });
            }
        }
        else if (target && !id) {
            if (target.getAttribute('data-state') === state.SAVE) {
                asyncAward(value, state.SAVE).then((response) => {
                    awardResult(value, response.result, submit, response.id);
                });
            }
            else if (target.getAttribute('data-state') === state.DELETE) {
                value.remove();
            }
        }
    });
}
function awardResult(form, result, message, id) {
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
