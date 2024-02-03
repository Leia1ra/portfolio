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
    const growth = document.querySelector("#growth-container");
    const append = document.querySelector('#growth-append');
    growth.querySelectorAll('form').forEach((value) => {
        growthProcess(value);
    });
    /*확장 버튼*/
    append.addEventListener('click', (e) => {
        const form = document.createElement('form');
        form.addEventListener('submit', (e) => {
            e.preventDefault();
        });
        form.method = 'post';
        form.action = '#newGrowth';
        form.innerHTML = `
			<div class="growth-details deletable-area">
				<label><span>시작일</span>
					<input type="month" name="startDate">
				</label>
				<label><span>종료일</span>
					<input type="month" name="endDate">
				</label>
				<label><span>장소</span>
					<input type="text" name="location">
				</label>
				<label><span>상세</span>
					<input type="text" name="details">
				</label>
				<label><span>기타</span>
					<input type="text" name="etc">
				</label>
				<button type="button" class="delete-button" data-state="delete"></button>
			</div>
			<input type="submit" class="save-button" data-state="save" value="작성 완료">
		`;
        growthProcess(form);
        growth.appendChild(form);
    });
});
function asyncGrowth(form, type) {
    return __awaiter(this, void 0, void 0, function* () {
        let url = '/personal/growth?';
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
function growthProcess(value) {
    value.addEventListener('click', (e) => {
        console.log(e.currentTarget);
        console.log(e.target);
        const target = e.target;
        const id = value.querySelector("input[name='id']");
        const submit = value.querySelector("input[type='submit']");
        if (target && id) {
            if (target.getAttribute('data-state') === state.SAVE) {
                asyncGrowth(value, state.SAVE).then((response) => {
                    growthResult(value, response.result, submit);
                });
            }
            else if (target.getAttribute('data-state') === state.DELETE) {
                asyncGrowth(value, state.DELETE).then((response) => {
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
                asyncGrowth(value, state.SAVE).then((response) => {
                    growthResult(value, response.result, submit, response.id);
                });
            }
            else if (target.getAttribute('data-state') === state.DELETE) {
                value.remove();
            }
        }
    });
}
function growthResult(form, result, message, id) {
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
