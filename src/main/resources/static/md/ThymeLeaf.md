# 타임리프의 주된 기능은 th:### 형식으로 입력함  

##### 문자 삽입
###### 직접 문자 삽입
```HTML
<div th:text="${msg}">속성 값에 지정된 값을 Sanitize 하여 출력합니다.</div>  
<div th:utext="${sanitize}">속성 값에 지정된 값을 출력합니다.</div>
```
	Sanitize : 위험한 코드나 데이터를 변환 또는 제거하여 무력화 하는 것
###### 인라인 처리
``` HTML
<div>안녕하세요! [[${msg}]]입니다</div>  
```
###### 값 결합
``` HTML
<div th:text="'안녕하세요! ' + ${msg} + '입니다'"></div>  
```
###### 리터럴 치환
```HTML
<div th:text="|안녕하세요! ${msg}입니다|"></div>  
```
###### 동적 주소
- Server의 Context 경로를 자동으로 붙여줌 
- 동적인 파라메터의 생성을 간편하게 지원함
```HTML
<a th:href="@{/community/write(id=${detail.id},name='leia1ra')}">글쓰기</a>
```
	 다수의 파라미터를 사용하기 위한 구분은 ,로 합니다.
###### Session
```HTML
<h1 th:text="${session['hello']}"></h1>  
```
###### Parameter
```HTML
<span th:text="${param.hello}"></span>
```
	 http://localhost:8080/community/?hello=gd
	 <span>gd</span>로 출력
---
##### 변수와 연산자

###### 지역변수
```HTML
<div th:with="a=1,b=2">  
	<span th:text="|${a} + ${b} = ${a+b}|"></span>  
</div>  
```
###### 비교와 등가
```HTML
<span th:text="1>=10">false</span>,  
<span th:text="1<=10">true</span>,  
<span th:text="1==10">false</span>,  
<span th:text="1!=10">true</span>,  
<span th:text="${msg}==thymeleaf">true</span>,  
<span th:text="${msg}!=thymeleaf">false</span>
<!--삼항연산자-->
<div th:text="${msg}=='thymeleaf'?'thymeleaf':'JSP'"></div>  
```
---
##### 조건 분기
###### if, unless 조건 분기문
```HTML
<div th:if="${msg}=='thymeleaf'">true : ㅎㅇ</div>  
<div th:unless="${msg}=='JSP'">false : ㅂㅇ</div>  
```
###### Switch 분기문
```HTML
<div th:switch="${msg}">  
	<span th:case="thymeleaf" th:text="|${msg} 입니다|"></span>  
	<span th:case="JSP" th:text="|구닥다리 ${msg} 입니다|"></span>  
	<span th:case="*">템플릿 엔진</span>  
</div> 
```
---
##### 참조
###### VO
```HTML
<form action="#">  
    vo Object 참조 ->  
    <input type="text" th:value="${hv.name}">  
    <input type="text" th:value="${hv['age']}">  
</form>
```

```HTML
<form action="#" method="post" th:object="${hv}">  
    vo th:Object 참조  
    <input type="text" th:value="*{name}">  
    <input type="text" th:value="*{['age']}">  
</form>
```
###### Map
```HTML
<div>  
	Map 데이터를 결합한 객체 참조 ->  
	<span th:text="${mp.get('name')}"></span>  
	<span th:text="${mp['type']}"></span>  
</div>  
```

```HTML
<div th:object="${mp}">  
	th:object 참조 ->  
	<span th:text="*{get('name')}"></span>  
	<span th:text="*{get('type')}"></span>  
</div>  
```
###### List
```HTML
<div>  
	List Object 참조  
	<span th:text="${li[0]}"></span>  
	<span th:text="${li[1]}"></span>  
	<span th:text="${li[2]}"></span>  
	<span th:text="${li[3]}"></span>  
</div>
```
---
##### 반복
```HTML
<div th:each="member : ${hli}">  
	<div th:text="|${member.name} : ${member.age}|"></div>  
</div>  
```
###### 상태변수
> \[요소 저장용 변수\], \[상태변수\] : \[ 반복처리할 객체\]
``` HTML
<div th:each="member, s : ${hli}" th:object="${member}" class="container">  
	<div th:text="|${s.index} : *{name} : *{age} |"></div>  
	count :   <span th:text="${s.count}">1부터 시작하는 인덱스</span>,  
	size :    <span th:text="${s.size}">반복처리하는 객체의 사이즈</span>,  
	current : <span th:text="${s.current}">현재 반복 요소의 객체를 표시</span>,  
	even :    <span th:text="${s.even}">현재 요소의 인덱스가 짝수(true)인지</span>,  
	odd :     <span th:text="${s.odd}">현재 요소의 인덱스가 홀수(true)인지</span>,  
	first :   <span th:text="${s.first}">첫번째 요소인지</span>,  
	last :    <span th:text="${s.last}">마지막 요소인지</span>  
</div>  
```

| 유틸리티 객체 | 기능 개요 |
| :---: | ---- |
| index | 0부터 시작하는 인덱스, 현재 인덱스를 표시합니다. |
| count | 1부터 시작하는 인덱스, 현재 인덱스를 표시합니다. |
| size | 반복 처리하는 객체와 사이즈를 표시합니다. |
| current | 현재 반복 요소의 객체를 표시합니다. |
| even | 현재 요소가 짝수 번째인지 여부를 결정합니다.<br>짝수이면 true, 홀수이면 false를 표시합니다. |
| odd | 현재 요소가 짝수 번째인지 여부를 결정합니다.<br>홀수이면 true, 짝수이면 false를 표시합니다. |
| first | 현재 요소가 첫 번째 요소인지 여부를 결정합니다. |
| last | 현재 요소가 마지막인지 여부를 결정합니다. |

---
##### 유틸리티 객체  
```HTML
<article class="container">  
    <h3>유틸리티 객체 : ${#~~~}을 통하여 다양한 기능 제공</h3>  
</article>
```

| 유틸리티 객체 | 기능 개요 |
| :--- | ---- |
| \#strings | 문자 관련 편의 기능 |
| \#numbers | 숫자 서식 지원 |
| \#bools | boolean관련 기능 |
| \#dates | java.util.Date 서식 지원 |
| \#objects | 객체 관련 기능 |
| \#arrays | 배열 관련 기능 |
| \#lists | List 관련 기능 |
| \#sets | Set 관련 기능 |
| \#maps | Map 관련 기능 |

---
##### 단편(fragment) 삽입
```HTML
<h3>fragment : 다른 Template 삽입하기</h3>  
<div id="one" th:insert="example/fragment :: one">
	fragment 태그 자체를 가져옴
</div>  
<div id="two" th:include="example/fragment :: two">
	fragment 태그 안의 값만 가져옴
</div>  
<div id="three" th:replace="/example/fragment :: three">
	th:replace가 선언된 태그는 fragment태그로 대체된다. 
</div>  
```
---
##### 빈블럭
> 타임리프 표현을 어느 곳에서든 사용할 수 있도록 하는 구문
```HTML
<th:block th:if="1=1">
	<div>ㅎㅇ</div>
</th:block>
```