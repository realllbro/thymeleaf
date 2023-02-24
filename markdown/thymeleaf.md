# Thymeleaf

[TOC]

## 1. 타임리프 소개

### 1-1. 타임리프 주요기능

* XML, XHTML 그리고 HTML5 를 위한 자바 템플릿 엔진이다. 다른 템플릿 포맷으로 확장이 가능하다.

* 웹과 오프라인 양쪽 환경에서 동작, 서블릿 API에 대한 강한 의존성이 없다.

* 다이얼렉트(dialect)라고 불리는 기능 셋 모듈을 기반으로 한다.

* 완벽한(그리고 확장가능한) 국제화를 지원한다.

  

### 1-2. 타임리프 뷰 구현

* 필요한 작업은 타임리프 템플릿 파일을 작성하는 것 밖에 없다.

* \src\main\resources\templates\ch0801\home.html

  ```html
  <!--타임리프가 사용된다는 것을 나타낸다.-->
  <html xmlns:th="http://www.thymeleaf.org">
  <head>
      <title>Home</title>
      <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
  </head>
  <body>
  <!--타임리프 마크업에는 th속성이라고 하는 th:로 시작하는 속성을 사용한다.-->
      <h1 th:text="${msg}">greeting</h1>
  </body>
  </html>
  ```

* \src\main\java\org.brolab.thymeleaf\Ch0801_Controller.java

  ```java
  package org.brolab.thymeleaf.controller.ch0801;
  
  import org.springframework.stereotype.Controller;
  import org.springframework.ui.Model;
  import org.springframework.web.bind.annotation.GetMapping;
  
  @Controller
  public class Ch0801_Controller {
  
      @GetMapping("ch0801/home")
      public String home0101(Model model){
          model.addAttribute("msg","<b>Hello world!</b>");
          return "ch0801/home";
      }
  }
  ```

  

## 2.텍스트 사용

### 2-1. th:text

* 이스케이프한 값을 표시한다.

* [요청] http://localhost:8080/ch0802/home0101

* [컨트롤러 메서드] 

  \src\main\java\org.brolab.thymeleaf\Ch0802_Controller.java

  ```java
  @GetMapping("ch0802/home0101")
  public String home0101(Model model){
    model.addAttribute("msg","<b>Hello world!</b>");
    return "ch0802/home0101";
  }
  ```

* [뷰 파일]

  \src\main\resources\templates\ch0802\home0101.html

  ```html
  <html xmlns:th="http://www.thymeleaf.org">
  <head>
      <title>Home</title>
      <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
  </head>
  <body>
      <p th:text="${msg}">greeting</p>
  </body>
  </html>
  ```

* [응답화면]

  ![](./img/1.png) 

### 2-2. th:utext

* HTML 태그가 적용된 형태로 표시한다.

* [요청] http://localhost:8080/ch0802/home0102

* [컨트롤러 메서드] 

  \src\main\java\org.brolab.thymeleaf\Ch0802_Controller.java

  ```java
  @GetMapping("ch0802/home0102")
  public String home0102(Model model){
    model.addAttribute("msg","<b>Hello world!</b>");
    return "ch0802/home0102";
  }
  ```

* [뷰 파일]

  \src\main\resources\templates\ch0802\home0102.html

  ```html
  <html xmlns:th="http://www.thymeleaf.org">
  <head>
      <title>Home</title>
      <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
  </head>
  <body>
      <p th:utext="${msg}">greeting</p>
  </body>
  </html>
  ```

* [응답화면]

  ![](./img/2.png)

### 2-3.th:value

* HTML 요소 값 

  ```html
  <html xmlns:th="http://www.thymeleaf.org">
  <head>
      <title>Home</title>
      <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
  </head>
  <body>
      <input type="text" th:value="${msg}">
  </body>
  </html>
  ```

  

## 3.표현식

th:text 속성이나 th:utext 속성을 사용하여 스프링 MVC 모델에 저장된 값 또는 프로퍼티 

파일에서 가져온 메시지를 표시한다.

### 3-1. 메시지

* 메시지 본문을 메시지의 키 값으로 가져 오려는 경우에 메시지 표현식을 사용한다.

* 메시지의 키 값을 사용하여 메시지 본문을 가져 온다.

* [요청] http://localhost:8080/ch0803/home0101

* [컨트롤러 메서드]

  \src\main\java\org.brolab.thymeleaf\Ch0803_Controller.java

  ```java
  @GetMapping("ch0803/home0101")
  public String home0200(Model model){
    model.addAttribute("msg", "Hello world!");
    return "ch0803/home0101";
  }
  ```

* [뷰 파일]

  \src\main\resources\templates\ch0801\home0101.html

  ```html
  <html xmlns:th="http://www.thymeleaf.org">
  <head>
      <title>Home</title>
      <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
  </head>
  <body>
  <h1 th:text="#{welcome.message}">greeting</h1>
  </body>
  </html>
  ```

* [응답화면]

  ![](./img/3.png) 

### 3-2. 변수

* 타임리프 변수 표현식에 OGNL 이라는 자바와 비슷한 언어를 기술해서 타임리프가 

  관리하는 변수에 접근하거나 메서드를 실행할 수 있다.


  #### 3-2-1. 단일 문자열 타입의 값을 가져온다.

  * [요청] http://localhost:8080/ch0803/home0201	

  * [컨트롤러 메서드]

    ```java
    @GetMapping("ch0803/home0201")
    public String home0201(Model model){
      model.addAttribute("msg", "Hello world!");
      return "ch0803/home0201";
    }
    ```

  * [뷰 파일]

    ```html
    <html xmlns:th="http://www.thymeleaf.org">
    <head>
        <title>Home</title>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    </head>
    <body>
    <h1 th:text="${msg}">greeting</h1>
    </body>
    </html>
    ```

  * [응답 화면]

    ![](./img/4.png)  


  #### 3-2-2. 자바빈즈 프러퍼티 값을 가져온다.

  * [요청] http://localhost:8080/ch0803/home0202

  * [컨트롤러 메서드]

    ```java
    @GetMapping("ch0803/home0202")
    public String home0202(Model model){
      Member member = new Member();

      member.setUserId("hongkd");
      member.setPassword("1234");
      member.setEmail("bbb@ccc.com");
      member.setUserName("홍길동");

      LocalDate dateOfBirth = LocalDate.of(1988, 10, 7);
      member.setDateOfBirth(dateOfBirth);

      model.addAttribute(member);

      return "ch0803/home0202";
    }
    ```

  * [뷰 파일]

    ```html
    <html xmlns:th="http://www.thymeleaf.org">
    <head>
        <title>Home</title>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    </head>
    <body>
      <table border ="1">
          <tr>
              <td>${member.userId}</td>
              <td th:text="${member.userId}">userId</td>
          </tr>
          <tr>
              <td>${member.password}</td>
              <td th:text="${member.password}">password</td>
          </tr>
          <tr>
              <td>${member.userName}</td>
              <td th:text="${member.userName}">userName</td>
          </tr>
          <tr>
              <td>${member.email}</td>
              <td th:text="${member.email}">email</td>
          </tr>
          <tr>
              <td>${member.dateOfBirth}</td>
              <td th:text="${member.dateOfBirth}">dateOfBirth</td>
          </tr>
      </table>
    </body>
    </html>
    ```

  * [응답 화면]
    ![](./img/5.png) 

  #### 3-2-3. 중첩된 자바빈즈 프러퍼티 값을 가져온다.
  * [요청] http://localhost:8080/ch0803/home0203

  * [컨트롤러 메서드]

    ```java
    @GetMapping("ch0803/home0203")
    public String home0203(Model model){
      Member member = new Member();

      Address address = new Address();
      address.setPostCode("080908");
      address.setLocation("seoul");

      member.setAddress(address);
      model.addAttribute(member);

      return "ch0803/home0203";
    }
    ```

  * [뷰 파일]

    ```html
    <html xmlns:th="http://www.thymeleaf.org">
    <head>
        <title>Home</title>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    </head>
    <body>
      <table border ="1">
          <tr>
              <td>${member.address.postCode}</td>
              <td th:text="${member.address.postCode}">postCode</td>
          </tr>
          <tr>
              <td>${member.address.location}</td>
              <td th:text="${member.address.location}">location</td>
          </tr>
      </table>
    </body>
    </html>
    ```

  * [응답 화면]

    ![](./img/6.png) 

### 3-3. 선택변수

특정 객체의 프로퍼티에 연속으로 접근하고 싶을 때 th:object 속성과 선택 변수 표현식을 

조합해서 사용하면 간단하게 기술할 수 있다.

#### 3-3-1. th:object table 태그에 사용 

* table 태그에 th:object 속성 값을 설정하고 내부 포함된 태그에 선택 표현식을 사용한다.

* [요청] http://localhost:8080/ch0803/home0301

* [컨트롤러 메서드] 

  ```java
  @GetMapping("ch0803/home0301")
  public String home0301(Model model){
    Member member = new Member();

    member.setUserId("hongkd");
    member.setPassword("1234");
    member.setEmail("bbb@ccc.com");
    member.setUserName("홍길동");

    LocalDate dateOfBirth = LocalDate.of(1988, 10, 7);
    member.setDateOfBirth(dateOfBirth);

    model.addAttribute(member);

    return "ch0803/home0301";
  }
  ```

* [뷰 파일]

  ```html
  <html xmlns:th="http://www.thymeleaf.org">
  <head>
      <title>Home</title>
      <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
  </head>
  <body>
    <table border ="1" th:object="${member}">
        <tr>
            <td>${member.userId}</td>
            <td th:text="*{userId}">userId</td>
        </tr>
        <tr>
            <td>${member.password}</td>
            <td th:text="*{password}">password</td>
        </tr>
        <tr>
            <td>${member.userName}</td>
            <td th:text="*{userName}">userName</td>
        </tr>
        <tr>
            <td>${member.email}</td>
            <td th:text="*{email}">email</td>
        </tr>
        <tr>
            <td>${member.dateOfBirth}</td>
            <td th:text="*{dateOfBirth}">dateOfBirth</td>
        </tr>
    </table>
  </body>
  </html>
  ```

* [응답 화면]

  ![](./img/7.png) 

  

#### 3-3-2. th:object form태그에 사용 

* form 태그에 th:object 속성 값을 설정하고 내부 포함된 태그에 선택 표현식을 사용한다.


* [요청] http://localhost:8080/ch0803/home0302

* [컨트롤러 메서드]

  ```java
  @GetMapping("ch0803/home0302")
  public String home0302(Model model){
    Member member = new Member();

    member.setUserId("hongkd");
    member.setPassword("1234");
    member.setEmail("bbb@ccc.com");
    member.setUserName("홍길동");

    LocalDate dateOfBirth = LocalDate.of(1988, 10, 7);
    member.setDateOfBirth(dateOfBirth);

    model.addAttribute(member);

    return "ch0803/home0302";
  }
  ```

* [뷰 파일]

  ```html
  <html xmlns:th="http://www.thymeleaf.org">
  <head>
      <title>Home</title>
      <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
  </head>
  <body>
  <form th:object="${member}">
    <table border ="1" >
        <tr>
            <td>${member.userId}</td>
            <td th:text="*{userId}">userId</td>
        </tr>
        <tr>
            <td>${member.password}</td>
            <td th:text="*{password}">password</td>
        </tr>
        <tr>
            <td>${member.userName}</td>
            <td th:text="*{userName}">userName</td>
        </tr>
        <tr>
            <td>${member.email}</td>
            <td th:text="*{email}">email</td>
        </tr>
        <tr>
            <td>${member.dateOfBirth}</td>
            <td th:text="*{dateOfBirth}">dateOfBirth</td>
        </tr>
    </table>
  </form>
  </body>
  </html>
  ```

* [응답 화면]

  ![](./img/8.png) 

  

### 3-4. 링크 URL 

링크 URL 표현식을 사용하여 지정된 URL의 시작 부분에 컨텍스트 경로를 추가 할 수 있다.

#### 3-4-1. th:href 링크 URL 표현식

* th:href 속성에 링크 URL 표현식을 사용하여 URL 값을 지정한다.

- [요청] http://localhost:8080/ch0803/home0401

- [컨트롤러 메서드]

  ```java
  @GetMapping("ch0803/home0401")
  public String home0401(Model model){
    return "ch0803/home0401";
  }
  ```

- [뷰 파일]

  ```html
  <html xmlns:th="http://www.thymeleaf.org">
  <head>
      <title>Home</title>
      <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
  </head>
  <body>
    <h2>Home0401</h2>
    <div>
        <a href="./sub/home0402.html" th:href="@{/ch0803/sub/home0402}">Home0402로 이동</a>
    </div>
  </body>
  </html>
  ```

- [응답 화면]

  ![](./img/9.png)  


#### 3-4-2. th:href 링크 URL 표현식 절대 URL, 상대 URL

* 컨텍스트 경로를 자동으로 추가하고 싶다면 상대 URL로 표현하고 "/" 로 시작해야 한다.

- [요청] http://localhost:8080/ch0803/home0403

- [컨트롤러 메서드]

  ```java
  @GetMapping("ch0803/home0403")
  public String home0403(Model model){
    return "ch0803/home0403";
  }
  ```

- [뷰 파일]

  ```html
  <html xmlns:th="http://www.thymeleaf.org">
  <head>
      <title>Home</title>
      <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
  </head>
  <body>
    <h4>절대 URL</h4>
    <a th:href="@{http://localhost:8080/board/list}">list</a>

    <h4>상대 URL - Context-relative</h4>
    <a th:href="@{/board/list}">list</a>

    <h4>상대 URL - Page-relative</h4>
    <a th:href="@{board/list}">list</a>

    <h4>상대 URL - Server-relative</h4>
    <a th:href="@{~/board/list}">list</a>
  </body>
  </html>
  ```

- [응답 화면]

  ![](./img/10.png) 

  

### 3-5. 리터럴

문자열을 표현하기 위한 텍스트 리터럴은 작은 따옴표로 둘러싼다. 

작은 따옴표가 문자열 안에 포함되어 있다면 ''로 이스케이프 해야 한다.

* 텍스트 리터럴은 작은 따옴표로 둘러싼다.

- [요청] http://localhost:8080/ch0803/home0501

- [컨트롤러 메서드]

  ```java
  @GetMapping("ch0803/home0501")
  public String home0501(Model model){
    return "ch0803/home0501";
  }
  ```

- [뷰 파일]

  ```html
  <html xmlns:th="http://www.thymeleaf.org">
  <head>
      <title>Home</title>
      <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
  </head>
  <body>
  <h1 th:text="'Hello world!'">greeting</h1>
  </body>
  </html>
  ```

- [응답 화면]

  ![](./img/11.png)  



### 3-6. 텍스트 추가

텍스트 리터럴을 '+'를 이용하여 연결할 수 있다.

* 텍스트 리터럴과 변수를 연결한다.

- [요청] http://localhost:8080/ch0803/home0601

- [컨트롤러 메서드]

  ```java
  @GetMapping("ch0803/home0601")
  public String home0601(Locale locale, Model model){
    LocalDateTime now = LocalDateTime.now();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 (E) a h시 m분 s초");
    String formattedNow = now.format(formatter);

    model.addAttribute("serverTime", formattedNow);
    return "ch0803/home0601";
  }
  ```

- [뷰 파일]

  ```html
  <html xmlns:th="http://www.thymeleaf.org">
  <head>
      <title>Home</title>
      <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
  </head>
  <body>
  <h1>Hello world!</h1>
  <p th:text = "'The time on the server is '+${serverTime}+'.'">시간 표시</p>
  <p th:text = "'The time on the server is '+'${serverTime}'+'.'">시간 표시</p>
  </body>
  </html>
  ```

- [응답 화면]

  ![](./img/12.png)  

### 3-6. 리터럴 대체

텍스트 리터럴 안에 치환 변수(${변수명})를 설정할 수 있다.

리터럴 치환을 사용하는 경우 치환하고 싶은 범위를 '|'로 감싼다.

* 텍스트 리터럴과 변수를 '|'로 감싼다.

- [요청] http://localhost:8080/ch0803/home0701

- [컨트롤러 메서드]

  ```java
  @GetMapping("ch0803/home0701")
  public String home0701(Locale locale, Model model){
    LocalDateTime now = LocalDateTime.now();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 (E) a h시 m분 s초");
    String formattedNow = now.format(formatter);

    model.addAttribute("serverTime", formattedNow);
    return "ch0803/home0701";
  }
  ```

- [뷰 파일]

  ```html
  <html xmlns:th="http://www.thymeleaf.org">
  <head>
      <title>Home</title>
      <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
  </head>
  <body>
  <h1>Hello world!</h1>
  <p th:text = "|The time on the server is ${serverTime}.|">시간 표시</p>
  <p th:text = "|The time on the server is '${serverTime}'.|">시간 표시</p>
  <p th:text = "|The time on the server is | + '${serverTime}' + |.|">시간 표시</p>
  </body>
  </html>
  ```

- [응답 화면]

  ![](./img/13.png) 

  

  

## 4. 속성 값 설정

타임 리프는 HTML5 및 XHTML에서 사용되는 일반적인 속성에 대한 전용 속성을 제공한다.

### 4-1. th:href

* href 속성에 th:href 전용 속성을 제공한다.

* [요청] http://localhost:8080/ch0804/home0101

* [컨트롤러 메서드]

  ```java
  @GetMapping("ch0804/home0101")
  public String home0101(Model model){
    return "ch0804/home0101";
  }
  ```

* [뷰 파일]

  ```html
  <html xmlns:th="http://www.thymeleaf.org">
  <head>
      <title>Home</title>
      <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
  </head>
  <body>
      <h1>Home0101</h1>
      <a href="ch0804/home0201.html" th:href="@{/ch0804/home0201}">Home0201</a>
  </body>
  </html>
  ```

* [응답 화면]

  ![](./img/14.png) 

### 4-2. th:action 

* action 속성에 th:action 전용 속성을 제공한다.

* [요청] http://localhost:8080/ch0804/home0201

* [컨트롤러 메서드]

  ```java
  @GetMapping("ch0804/home0201")
  public String home0201(Model model){
    return "ch0804/home0201";
  }
  ```

* [뷰 파일]

  ```html
  <html xmlns:th="http://www.thymeleaf.org">
  <head>
      <title>Home</title>
      <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
  </head>
  <body>
      <h1>Home0201</h1>

      <form id="board" action="success.html" th:action="@{ch0804/register}" method="POST">
          <table>
              <tr>
                  <td>Title</td>
                  <td><input type="text" name="title"/></td>
              </tr>
          </table>
          <button type="submit" id="btnRegister">Register</button>
      </form>
  </body>
  </html>
  ```

* [응답 화면]

  ![](./img/15.png) 

  

### 4-3. th:src

* src 속성에 th:src 전용 속성을 제공한다.

* [요청] http://localhost:8080/ch0804/home0301

* [컨트롤러 메서드]

  ```java
  @GetMapping("ch0804/home0301")
  public String home0301(Model model){
    return "ch0804/home0301";
  }
  ```

* [뷰 파일]

  ```html
  <html xmlns:th="http://www.thymeleaf.org">
  <head>
      <title>Home</title>
      <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
  </head>
  <body>
      <h1>Home0301</h1>
      <img src="../static/images/player.png" th:src="@{/static/images/player.png}" />
      <img src="../static/images/player.png" th:src="@{../static/images/player.png}" />
      <img src="../../static/images/player.png" />

      <img src="../images/player.png" th:src="@{/images/player.png}" />
      <img src="../images/player.png" th:src="@{../images/player.png}" />
      <img src="../../images/player.png" />
  </body>
  </html>
  ```

* [응답 화면]

  ![](./img/16.png) 



## 5.제어속성

HTML 요소의 표시를 제어해야 하는 경우가 있는데 타임리프에서는 이런 기능을 th 속성으로 제공된다.



### 5-1. th:if

* 속성 값이 참인 경우에만 대상이 되는 HTML 요소를 표시한다.

  [참과 거짓 판단 기준]

  * null 인 경우는 거짓
  * 숫자 타입에서 0이 아닌 값이면 참, 그렇지 않은 경우는 거짓
  * 문자열 타입에서 false, off, no 이면 거짓 그렇지 않은 경우는 참
  * boolean  타입, 숫자 타입, 문자열 타입 이외의 경우에는 참

  #### 5-1-1.자바빈즈 프로퍼티 값이 null 인지 체크한다.

  - [요청] http://localhost:8080/ch0805/home0101

  - [컨트롤러 메서드]

    ```java
    @GetMapping("ch0805/home0101")
    public String home0101(Model model) {
      Member member = new Member();

      model.addAttribute(member);

      return "ch0805/home0101";
    }
    ```

  - [뷰 파일]

    ```html
    <html xmlns:th="http://www.thymeleaf.org">

    <head>
    	<title>Home</title>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    </head>
    <body>
    	<th:block th:if="${member.hobbyArray == null}">
      		<p>member.hobbyArray == null</p>
    	</th:block>	
    	
    	<th:block th:if="${member.hobbyArray eq null}">
      		<p>member.hobbyArray eq null</p>
    	</th:block>	
    </body>
    </html>
    ```

  - [응답 화면]

    ![](./img/17.png)  

  #### 5-1-2.자바빈즈 프로퍼티 값이 참 인지 체크한다.

  - [요청] http://localhost:8080/ch0805/home0102

  - [컨트롤러 메서드]

    ```java
    @GetMapping("ch0805/home0102")
    public String home0102(Model model) {
      Member member = new Member();
      member.setForeigner(true);

      model.addAttribute(member);

      return "ch0805/home0102";
    }
    ```

  - [뷰 파일]

    ```html
    <html xmlns:th="http://www.thymeleaf.org">

    <head>
    	<title>Home</title>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    </head>
    <body>
    	<th:block th:if="${member.foreigner}">
      		<p>member.foreigner == true</p>
    	</th:block>
    </body>
    </html>
    ```

  - [응답 화면]

    ![](./img/18.png)
  
  #### 5-1-3. javascript 에서 사용
  
  - [뷰 파일]
  
    ```javascript
    //if문
    [# th:if="${users.executivelist.size > 1}" ]
     .addSelect('empNo', '사용자', 'i18n', 100, false, {
         values: [],
         labels: [],
         editOptions: {
             listItems: executiveList
         },
         align: 'center',
         editable: true
     })         	
     [/]
    //else문
    [# th:unless="${users.executivelist.size > 1}" ]
       .add('empNo','사용자ID','i18n', 100,   false, {editable: true, visible: false})  
    [/]  
    
    또는 아래처럼 /**/ 붙여서 사용 
     
    /*[# th:if="${custInfo != null && custInfo != ''}"]*/
        var custId = $('#joinForm input[name=custId]').val();
    /*[/]*/
    ```


### 5-2.th:unless

* 속성 값이 거짓인 경우에만 대상이 되는 HTML 요소를 표시한다.

  #### 5-2-1. 자바빈즈 프로퍼티 값이 거짓인지 체크한다.

  - [요청] http://localhost:8080/ch0805/home0103

  - [컨트롤러 메서드]

    ```java
    @GetMapping("ch0805/home0103")
    public String home0103(Model model) {
      Member member = new Member();
      member.setForeigner(false);

      model.addAttribute(member);

      return "ch0805/home0103";
    }
    ```

  - [뷰 파일]

    ```html
    <html xmlns:th="http://www.thymeleaf.org">

    <head>
    	<title>Home</title>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    </head>
    <body>
    	<th:block th:unless="${member.foreigner}">
      		<p>member.foreigner == false</p>
    	</th:block>
    </body>
    </html>

    ```

  - [응답 화면]

    ![](./img/19.png) 

### 5-3. th:switch - th:case

* th:switch 속성: 자식 요소에 기술된 th:case 속성의 값과 비교 평가를 하기 위한 값을 설정한다.

* th:case 속성: 부모 요소의 th:switch 속성 값의 경우에만 대상이 되는 HTML 요소를 표시한다.

  #### 5-3-1. th:switch 1

  * th:switch 속성 값을 비교하여 일치하는 값이 없으면 th:case="*"의 HTML 요소를 표시한다.

  * [요청] http://localhost:8080/ch0805/home0201

  * [컨트롤러 메서드]

    ```java
    @GetMapping("ch0805/home0201")
    public String home0201(Model model) {
      Member member = new Member();

      model.addAttribute(member);

      return "ch0805/home0201";
    }
    ```

  * [뷰 파일]

    ```html
    <html xmlns:th="http://www.thymeleaf.org">

    <head>
    	<title>Home</title>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    </head>
    <body>
    	<div th:switch="${member.gender}">
    		<p th:case="M">male</p>
    		<p th:case="F">female</p>
    		<p th:case="*">others</p>
    	</div>
    </body>
    </html>
    ```

  * [응답 화면]

    ![](./img/20.png) 

  #### 5-3-2. th:switch 2

  th:switch 속성 값을 비교하여 일치하는 값을 가진 th:case의 HTML 요소를 표시한다.

  - [요청] http://localhost:8080/ch0805/home0202

  - [컨트롤러 메서드]

    ```java
    @GetMapping("ch0805/home0202")
    public String home0202(Model model) {
      Member member = new Member();
      member.setGender("F");

      model.addAttribute(member);

      return "ch0805/home0201";
    }
    ```

  - [뷰 파일]

    ```html
    <html xmlns:th="http://www.thymeleaf.org">

    <head>
    	<title>Home</title>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    </head>
    <body>
    	<th:block th:switch="${member.gender}">
    		<p th:case="M">male</p>
    		<p th:case="F">female</p>
    		<p th:case="*">others</p>
    	</th:block>
    </body>
    </html>

    ```

  - [응답 화면]

    ![](./img/21.png) 

### 5-4. th:each

* 반복되는 HTML 요소를 표시한다.

  #### 5-4-1.배열 타입의 자바빈즈 프로퍼티 값을 반복하여 표시한다.

  * [요청] http://localhost:8080/ch0805/home0301

  * [컨트롤러 메서드]  

    ```java
    @GetMapping("ch0805/home0301")
    public String home0301(Model model) {
      Member member = new Member();

      String[] hobbyArray = {"Music", "Movie"};

      member.setHobbyArray(hobbyArray);

      model.addAttribute(member);

      return "ch0805/home0301";
    }
    ```

  * [뷰 파일]

    ```html
    <html xmlns:th="http://www.thymeleaf.org">

    <head>
    	<title>Home</title>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    </head>
    <body>
    	<th:block th:each="hobby : ${member.hobbyArray}">
    		<p th:text="${hobby}">hobby</p>
    	</th:block>
    </body>
    </html>
    ```

  * [응답 화면]

    ![](./img/22.png) 
    
  * ##### 5-4-1-1. 반복 상태 변수 "Stat"

    * **Thymeleaf**에서 **th:each** 사용하면 반복 상태를 추적할 수 있는 **status 변수**를 제공해 주는데 

      이를 이용하여 **index, count** 등의 값을 쉽게 추출 할 수 있습니다.

    * status 변수는 기본적으로 **반복대상 오브젝트명 + "Stat"** 변수명으로 접근 할 수 있으며 
  
      th:each 선언**시 개발자가 직접 명명하여 사용 할 수도 있습니다.**
  
      ```javascript
      index	현재 반복 인덱스  (0부터 시작)		
      count	현재 반복 인덱스  (1부터 시작)	
      size	총 요소 수
      current	현재 요소
      even	현재 반복이 짝수인지 여부 (boolean) 
      odd	현재 반복이 홀수인지 여부 (boolean)
      first	현재 반복이 첫번째인지 여부 (boolean) 
      last	현재 반복이 마지막인지 여부 (boolean)
      
      <!--/* <div th:each="num, numStat : ${#numbers.sequence(1,3)}"> */ -->
      <div th:each="num : ${#numbers.sequence(1,3)}">
      	<p th:text="${'index : ' + numStat.index}"></p>
      	<p th:text="${'count : ' + numStat.count}"></p>
      	<p th:text="${'size : ' + numStat.size}"></p>
      	<p th:text="${'current : ' + numStat.current}"></p>
      	<p th:text="${'even : ' + numStat.even}"></p>
      	<p th:text="${'odd : ' + numStat.odd}"></p>
      	<p th:text="${'first : ' + numStat.first}"></p>
      	<p th:text="${'last : ' + numStat.last}"></p>
      </div>
      
      ※ 위에 코드 실행시 결과
      <div>
      	<p>index : 0</p>
      	<p>count : 1</p>
      	<p>size : 3</p>
      	<p>current : 1</p>
      	<p>even : true</p>
      	<p>odd : false</p>
      	<p>first : true</p>
      	<p>last : false</p>
      </div>
      <div>
      	<p>index : 1</p>
      	<p>count : 2</p>
      	<p>size : 3</p>
      	<p>current : 2</p>
      	<p>even : false</p>
      	<p>odd : true</p>
      	<p>first : false</p>
      	<p>last : false</p>
      </div>
      <div>
      	<p>index : 2</p>
      	<p>count : 3</p>
      	<p>size : 3</p>
      	<p>current : 3</p>
      	<p>even : true</p>
      	<p>odd : false</p>
      	<p>first : false</p>
      	<p>last : true</p>
      </div>
      ```

      

  
  #### 5-4-2. 컬렉션 리스트 타입의 자바빈즈 프로퍼티 값을 반복하여 표시한다.

  * [요청] http://localhost:8080/ch0805/home0302
  
  * [컨트롤러 메서드]  
  
    ```java
    @GetMapping("ch0805/home0302")
    public String home0302(Model model) {
      Member member = new Member();

      List<String> hobbyList = new ArrayList<String>();
      hobbyList.add("Music");
      hobbyList.add("Movie");

      member.setHobbyList(hobbyList);

      model.addAttribute(member);
  
      return "ch0805/home0302";
    }
    ```
  
  * [뷰 화면]
  
    ```html
    <html xmlns:th="http://www.thymeleaf.org">
  
    <head>
    	<title>Home</title>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    </head>
    <body>
    	<th:block th:each="hobby : ${member.hobbyList}">
    		<p th:text="${hobby}">hobby</p>
    	</th:block>
    </body>
    </html>
    ```
  
  * [응답 화면]

    ![](./img/23.png)  

  #### 5-4-3. 자바빈즈 컬렉션 리스트
  
  * 자바빈즈 컬렉션 리스트 요소를 가진 컬렉션 리스트 타입의 자바빈즈 프로퍼티 값을 반복하여 표시한다.
  
  - [요청] http://localhost:8080/ch0805/home0303
  
  - [컨트롤러 메서드]
  
    ```java
    @GetMapping("ch0805/home0303")
    public String home0303(Model model) {
      Member member = new Member();
  
      List<Card> cardList = new ArrayList<Card>();
  
      Card card1 = new Card();
      card1.setNo("123456");
  
      YearMonth validMonth = YearMonth.of(2020, 9);
      card1.setValidMonth(validMonth);
  
      cardList.add(card1);
  
      Card card2 = new Card();
      card2.setNo("456786");
  
      YearMonth validMonth2 = YearMonth.of(2022, 11);
      card2.setValidMonth(validMonth2);
  
      cardList.add(card2);
  
      member.setCardList(cardList);;
  
      model.addAttribute(member);
  
      return "ch0805/home0303";
    }
    ```
  
  - [뷰 파일]
  
    ```html
    <html xmlns:th="http://www.thymeleaf.org">
  
    <head>
    	<title>Home</title>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    </head>
    <body>
    	<th:block th:each="card : ${member.cardList}">
    		<span th:text="${card.no}">card.no</span>
    		<span th:text="${card.validMonth}">card.validMonth</span>
    		<br />
    	</th:block>
    	
    	<br />
    	
    	<th:block th:each="card : ${member.cardList}">
    		[[${card.no}]]
    		[[${card.validMonth}]]
    		<br />
    	</th:block>	
    </body>
    </html>
    ```
  
  - [응답 화면]
  
    ![](./img/24.png) 



### 5-5. th:with

- 타임리프는 th:with 속성을 사용하여 반복없이 지역 변수를 선언하는 방법을 제공한다.

  #### 5-5-1.지역 변수

  * 지역 변수를 선언한 후 자바빈즈 프로퍼티 값을 그 지역 변수에 대입하여 사용한다.

  - [요청] http://localhost:8080/ch0805/home0402

  - [컨트롤러 메서드]  

    ```java
    @GetMapping("ch0805/home0402")
    public String home0402(Model model) {
      Member member = new Member();

      member.setUserId("hongkd");

      model.addAttribute(member);

      return "ch0805/home0402";
    }
    ```

  - [뷰 파일]

    ```html
    <html xmlns:th="http://www.thymeleaf.org">

    <head>
    	<title>Home</title>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    </head>
    <body>
    	<table border="1" th:with="memberId=${member.userId}">
    		<tr>
    			<td>${member.userId}</td>
    			<td th:text="${memberId}">member.userId</td>
    		</tr>
    	</table> 
    </body>
    </html>
    ```

  - [응답 화면]

    ![](./img/26.png)  

  

## 6. 인라인

타임리프 태그 속성을 사용하여 거의 모든 것을 처리 할 수 있지만 HTML 텍스트에 직접 표현을 쓰는 것이 

더 좋을 수도 있다. 인라인 표기법을 사용하면 HTML 텍스트를 직접 표현할 수 있다.



### 6-1. 인라인 표기법

- 인라인 표기법은 [[...]] 또는 [(...)] 형태로 표현된다.

  [[...]]는 th:text 속성에 해당하고 [(...)]는 th:utext 속성에 해당한다.

  #### 6-1-1.스프링 MVC 모델에 저장된 값을 인라인 표기법을 사용하여 표시한다.

  - [요청] http://localhost:8080/ch0806/home0101

  - [컨트롤러 메서드]

    ```java
    @GetMapping("ch0806/home0101")
    public String home0101(Model model) {
      model.addAttribute("username", "Sebastian");

      return "ch0806/home0101";
    }
    ```

  - [뷰 파일]

    ```html
    <html xmlns:th="http://www.thymeleaf.org">

    <head>
    	<title>Home</title>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    </head>
    <body>
    	<p th:text="|Hello, ${username}!|">greeting</p>
    	<p>Hello, <span th:text="${username}">name</span>!</p>
    	<p>Hello, [[${username}]]!</p>
    </body>
    </html>
    ```

  - [응답 화면]

    ![](./img/27.png)  

  #### 6-1-2.[(...)] 인라인 표기법은 이스케이프 처리를 하지 않는다.

  - [요청] http://localhost:8080/ch0806/home0102

  - [컨트롤러 메서드]

    ```java
    @GetMapping("ch0806/home0102")
    public String home0102(Model model) {
      model.addAttribute("username", "<b>Sebastian</b>");

      return "ch0806/home0102";
    }
    ```

  - [뷰 파일]

    ```html
    <html xmlns:th="http://www.thymeleaf.org">

    <head>
    	<title>Home</title>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    </head>
    <body>
    	<p th:utext="|Hello, ${username}!|">greeting</p>
    	<p>Hello, <span th:utext="${username}">name</span>!</p>
    	<p>Hello, [(${username})]!</p>
    </body>
    </html>
    ```

  - [응답 화면]

    ![](./img/28.png)   



### 6-2. 비활성화

- th:inline 속성 값을 "none" 으로 지정하면 인라인 표기법을 비활성화 할 수 있다.

- 비활성화가 된 상태에서는 인라인 표기법의 형태인 [(...)] 로 그대로 표시된다.

- [요청] http://localhost:8080/ch0806/home0201

- [컨트롤러 메서드]

  ```java
  @GetMapping("ch0806/home0201")
  public String home0201(Model model) {
    model.addAttribute("username", "<b>Sebastian</b>");

    return "ch0806/home0201";
  }
  ```

- [뷰 파일]

  ```html
  <html xmlns:th="http://www.thymeleaf.org">

  <head>
  	<title>Home</title>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  </head>
  <body>
  	<p>Hello, [[${username}]]!</p>
  	<p>Hello, [(${username})]!</p>
  	<p th:inline="none">Hello, [[${username}]]!</p>
  </body>
  </html>
  ```

- [응답 화면]

  ![](./img/29.png)   

### 6-3. 텍스트 인라이닝

- th:inline 속성 값을 "text" 로 지정하면 인라인 표현이 텍스트 형태로 표시된다.

- 인라인 표현이 텍스트 형태로 표시된다.

- [요청] http://localhost:8080/ch0806/home0301

- [컨트롤러 메서드]

  ```java
  @GetMapping("ch0806/home0301")
  public String home0301(Model model) {
    model.addAttribute("username", "Sebastian");

    return "ch0806/home0301";
  }
  ```

- [뷰 파일]

  ```html
  <html xmlns:th="http://www.thymeleaf.org">

  <head>
  	<title>Home</title>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  </head>

  <script th:inline="text">

  	var username = [[${username}]];

  </script>

  <body>
  	<p th:text="|Hello, ${username}!|">greeting</p>
  	<p>Hello, <span th:text="${username}">name</span>!</p>
  	<p>Hello, [[${username}]]!</p>
  </body>
  </html>
  ```

- [응답 화면]

  ![](./img/30.png)    

### 6-4. JavaScript 인라이닝

- th:inline 속성 값을 "javascript" 로 지정하면 인라인 표현이 자바스크립트 문법을 고려하여 표시된다.

- 인라인 표현이 자바스크립트 문자열 형태로 표시된다.

- [요청] http://localhost:8080/ch0806/home0401

- [컨트롤러 메서드]

  ```java
  @GetMapping("ch0806/home0401")
  public String home0401(Model model) {
    model.addAttribute("username", "Sebastian");

    return "ch0806/home0401";
  }
  ```

- [뷰 파일]

  ```html
  <html xmlns:th="http://www.thymeleaf.org">

  <head>
  	<title>Home</title>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  </head>

  <script th:inline="javascript">

  	var username = [[${username}]];

  </script>

  <body>
  	<p th:text="|Hello, ${username}!|">greeting</p>
  	<p>Hello, <span th:text="${username}">name</span>!</p>
  	<p>Hello, [[${username}]]!</p>
  </body>
  </html>
  ```

- [응답 화면]

  ![](./img/31.png) 

## 7. 주석

### 7-1. 표준 HTML / XML 주석

- 일반적인 HTML 처럼 &lt;!-- 와 --> 사이의 구문을 주석으로 처리한다.

- 타임리프 처리 후에도 템플릿에 남아 있다.

- [요청] http://localhost:8080/ch0807/home0101

- [컨트롤러 메서드]

  ```java
  @GetMapping("ch0807/home0101")
  public String home0101(Model model) {
    model.addAttribute("msg", "Hello world!");

    return "ch0807/home0101";
  }
  ```

- [뷰 파일]

  ```html
  <html xmlns:th="http://www.thymeleaf.org">

  <head>
  	<title>Home</title>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  </head>
  <body>
  	<!-- User info follows -->
  	<h1 th:text="${msg}">greeting</h1>
  </body>
  </html>
  ```

- [응답 화면]

  ![](./img/32.png)  



### 7-2. Thymeleaf 파서 수준 주석 블록

- &lt;!-- /* 와  */--> 사이의 구문을 주석으로 처리한다.

- 타임리프 처리 할 때 삭제된다.

- [요청] http://localhost:8080/ch0807/home0201

- [컨트롤러 메서드]

  ```java
  @GetMapping("ch0807/home0201")
  public String home0201(Model model) {
    model.addAttribute("msg", "Hello world!");

    return "ch0807/home0201";
  }
  ```

- [뷰 파일]

  ```html
  <html xmlns:th="http://www.thymeleaf.org">

  <head>
  	<title>Home</title>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  </head>
  <body>
  	<!--/* This code will be removed at Thymeleaf parsing time! */-->
  	<h1 th:text="${msg}">greeting</h1>
  </body>
  </html>
  ```

- [응답 화면]

  ![](./img/33.png)  



## 8.템플릿 프래그먼트

여러 템플릿을 작성할 때 공통적인 내용을 별도 파일로 추출하여 

효율적으로 사용할 수 있게 해주는 기능이다.



### 8-1. 프래그먼트 정의(th:fragment)

* th:fragment 속성을 이용하여 공통적으로 사용할 부분을 프래그먼트로 정의한다.


* [헤더 프레그먼트]

  ```html
  <html xmlns:th="http://www.thymeleaf.org">

  <body>
  	<div th:fragment="header">
  		<p>페이지 헤더</p>
  	</div>
  </body>
  </html>
  ```

* [푸터 프래그먼트]

  ```html
  <html xmlns:th="http://www.thymeleaf.org">
  
  <body>
  	<div th:fragment="footer">
  		<p>© 2019 ALL RIGHT RESERVED</p>
  	</div>
  </body>
  </html>
  ```



### 8-2. 프래그먼트 참조

* 정의한 프래그먼트를 참조해서 템플릿에 불러온다.

  ```html
  <html>
  <head>
  	<title>Home</title>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  </head>
  <body>
  	<div>
  		<p>페이지 헤더</p>
  	</div>
  	
  	<div>
  		<h1>Hello world!</h1>
  	</div>
  	
  	<div>
  		<p>© 2019 ALL RIGHT RESERVED</p>
  	</div>
  </body>
  </html>
  ```



### 8-3.th:insert

* th:insert 속성을 이용하여 프래그먼트를 삽입한다.

* [요청] http://localhost:8080/ch0808/home0101

* [컨트롤러 메서드]

  ```java
  @GetMapping("ch0808/home0101")
  public String home0101(Model model) {
    model.addAttribute("msg", "Hello world!");

    return "ch0808/home0101";
  }
  ```

* [뷰 파일]

  ```html
  <html xmlns:th="http://www.thymeleaf.org">

  <head>
  	<title>Home</title>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  </head>
  <body>
  	<div th:insert="~{ch0808/fragments/header::header}"></div>
  	
  	<div>
  		<h1 th:text="${msg}">greeting</h1>
  	</div>
  	
  	<div th:insert="~{ch0808/fragments/footer::footer}"></div>
  </body>
  </html>
  ```

* [응답 화면]

  ![](./img/34.png)  



### 8-4.th:replace

- th:replace 속성을 이용하여 프래그먼트를 대체한다.

- insert 추가와 replace 교환은 타임리프 가 파싱 되고 나서 순수 html 코드 소스 보기 하면 
  처리된 내용이 다르다. (insert 일 경우 html 요소가 추가되는 차이)

- [요청] http://localhost:8080/ch0808/home0201

- [컨트롤러 메서드]

  ```java
  @GetMapping("ch0808/home0201")
  public String home0201(Model model) {
    model.addAttribute("msg", "Hello world!");

    return "ch0808/home0201";
  }
  ```

- [뷰 파일]

  ```html
  <html xmlns:th="http://www.thymeleaf.org">

  <head>
  	<title>Home</title>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  </head>
  <body>
  	<div th:replace="~{ch0808/fragments/header::header}"></div>
  	
  	<div>
  		<h1 th:text="${msg}">greeting</h1>
  	</div>
  	
  	<div th:replace="~{ch0808/fragments/footer::footer}"></div>
  </body>
  </html>
  ```

- [응답 화면]

  ![](./img/35.png) 



## 9.레이아웃 재사용

타임리프 레이아웃 다이얼렉트를 이용하여 레이아웃을 재사용할 수 있다.



### 9-1. 환경설정

- 의존관계 정의

  ```xml
  #maven
  <dependency>
    <groupId>nz.net.ultraq.thymeleaf</groupId>
    <artifactId>thymeleaf-layout-dialect</artifactId>
  </dependency>  
  
  #gradle
  dependencies {
  	implementation('nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect')
  }
  ```

### 9-2. 공통 레이아웃 템플릿 정의

* 공통 레이아웃이 되는 템플릿을 'Decorator' 라고 한다.

* main_template.html

  ```html
  <html xmlns:th="http://www.thymeleaf.org"
  	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
  
  <head>
  	<title>Main</title>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  	<script src="/js/jQuery-2.1.4.min.js"></script>
  </head>
  
  <body>
  
  	<div>
  		<p>페이지 헤더</p>
  	</div>
  	
  	<div layout:fragment="content">
  		
  	</div>
  	
  	<div>
  		<p>© 2019 ALL RIGHT RESERVED</p>
  	</div>
  
  </body>
  </html>
  
  ```



### 9-2. 레이아웃 적용 템플릿

* 대체하고 싶은 요소에 layout:fragment 속성을 부여한다.

* [요청] http://localhost:8080/ch0809/home0101

* [컨트롤러 메서드]

  ```java
  @GetMapping("ch0809/home0101")
  public String home0101(Model model) {
    model.addAttribute("msg", "Hello world!");

    return "ch0809/home0101";
  }
  ```

* [뷰 파일]

  ```html
  <html xmlns:th="http://www.thymeleaf.org"
  	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
  	layout:decorate="~{ch0809/layouts/main_template}">
  <head>
  	<title>Home</title>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  	<link rel="stylesheet" href="../../static/css/style.css" th:href="@{/css/style.css}"/>
  </head>
  <body>
  <div layout:fragment="content">
  	<p>Home0101 Content</p>
  </div>
  </body>
  </html>
  ```

* [응답 화면]

  ![](./img/36.png)  



### 

## 10.유틸리티 객체

유틸리티 객체는 기존처럼 메서드를 호출하는 방식으로 사용할 수 있는 객체들이다.



### 10-1.숫자관련 #numbers

* 숫자에 대한 포매팅을 처리할 때 주로 사용된다.

* [요청] http://localhost:8080/ch0810/home0101

* [컨트롤러 메서드]

  ```java
  @GetMapping("ch0810/home0101")
  public String home0101(Model model) {
    int coin = 1000;

    model.addAttribute("coin", coin);

    return "ch0810/home0101";
  }
  ```

* [뷰 파일]

  ```html
  <html xmlns:th="http://www.thymeleaf.org">

  <head>
  	<title>Home</title>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  </head>
  <body>
  <table border="1">
  	<tr>
  		<td>${coin}</td>
  		<td th:text="${coin}"></td>
  	</tr>
  	<tr>
  		<td>${#numbers.formatInteger(coin,3,'COMMA')}</td>
  		<td th:text="${#numbers.formatInteger(coin,3,'COMMA')}"></td>
  	</tr>
  	<tr>
  		<td>${#numbers.formatCurrency(coin)}</td>
  		<td th:text="${#numbers.formatCurrency(coin)}"></td>
  	</tr>
  	<tr>
  		<td>
  			<div>priceValue=99.87654</div>
  			<div>${#numbers.formatInteger(priceValue,3,'COMMA')}</div>
  			<div>${#numbers.formatDecimal(priceValue,5,10,'POINT')}</div>
  		</td>
  		<td>
  		<div th:with="priceValue=99.87654">
  			<p th:text="${#numbers.formatInteger(priceValue,3,'COMMA')}"></p>
  			<p th:text="${#numbers.formatDecimal(priceValue,5,10,'POINT')}"></p>
  		</div>
  		</td>
  	</tr>
  </table>
  </body>
  </html>

  ```

* [응답 화면]

  ![](./img/37.png)
  
  #### 10-1-2  .#numbers.sequence
  
  * 컬렉션 없이 **단순 반복 처리**를 하고 싶다면 **Numbers Class**(**org.thymeleaf.expression.Numbers**)
  
    의 Utility method인 **#numbers.sequence**을 사용하여 먼저 원하는 반복 횟수 만큼의 
  
    **배열을 생성한 뒤 th:each 의 컬렉션에 넣어 주시면 됩니다.**
  
    ```javascript
    /* Create a sequence (array) of integer numbers going from x to y */
    ${#numbers.sequence(from,to)}
    ${#numbers.sequence(from,to,step)}
    
    <!-- html (Thymeleaf) --> 
    <th:block th:each="num : ${#numbers.sequence(1,5)}">
    	<div th:text="${num}"></div>
    </th:block>
    ```

### 10-2.날짜관련 #dates, #calendars

- 자바의 java.util.Date와 java.util.Calendar의 기능을 이용한다.

- [요청] http://localhost:8080/ch0810/home0201

- [컨트롤러 메서드]

  ```java
  @GetMapping("ch0810/home0201")
  public String home0201(Model model) {
    Date date = new Date();

    model.addAttribute("now", date);

    return "ch0810/home0201";
  }
  ```

- [뷰 파일]

  ```html
  <html xmlns:th="http://www.thymeleaf.org">

  <head>
  	<title>Home</title>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  </head>
  <body>
  <table border="1">
  	<tr>
  		<td>${now}</td>
  		<td th:text="${now}"></td>
  	</tr>
  	<tr>
  		<td>${#dates.format(now, 'yyyy-MM-dd')}</td>
  		<td th:text="${#dates.format(now, 'yyyy-MM-dd')}"></td>
  	</tr>
  	<tr>
  		<td>${#dates.format(now, 'yyyy-MM-dd HH:mm:ss')}</td>	
  		<td th:text="${#dates.format(now, 'yyyy-MM-dd HH:mm:ss')}"></td>
  	</tr>
  	<tr>
  		<td>${#dates.format(now, 'a h:mm')}</td>
  		<td th:text="${#dates.format(now, 'a h:mm')}"></td>
  	</tr>
  	<tr>
  		<td>${#dates.format(now, 'z a h:mm')}</td>	
  		<td th:text="${#dates.format(now, 'z a h:mm')}"></td>
  	</tr>
  	<tr>
  		<td>timeValue=${#dates.createToday()}</td>	
  		<td>
  			<div th:with="timeValue=${#dates.createToday()}">
  				<p>[[${timeValue}]]</p>
  			</div>
  		</td>
  	</tr>
  </table>
  </body>
  </html>

  ```

- [응답 화면]

  ![](./img/38.png) 

### 10-3.문자관련 #strings

- 문자열을 처리하기 위한 기능을 지원한다.

- [요청] http://localhost:8080/ch0810/home0301

- [컨트롤러 메서드]

  ```java
  @GetMapping("ch0810/home0301")
  public String home0301(Model model) {
    String str = "Hello, World!";

    model.addAttribute("str", str);

    return "ch0810/home0301";
  }
  ```

- [뷰 파일]

  ```html
  <html xmlns:th="http://www.thymeleaf.org">

  <head>
  	<title>Home</title>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  </head>
  <body>
  	<table border="1">
  		<tr>
  			<td>${str}</td>
  			<td th:text="${str}"></td>
  		</tr>
  		<tr>
  			<td>${#strings.contains(str, 'Hello')}</td>	
  			<td th:text="${#strings.contains(str, 'Hello')}"></td>
  		</tr>
  		<tr>
  			<td>${#strings.containsIgnoreCase(str, 'Hello')}</td>	
  			<td th:text="${#strings.containsIgnoreCase(str, 'Hello')}"></td>
  		</tr>
  		<tr>
  			<td>${#strings.startsWith(str, 'Hello')}</td>	
  			<td th:text="${#strings.startsWith(str, 'Hello')}"></td>
  		</tr>
  		<tr>
  			<td>${#strings.endsWith(str, 'World!')}</td>	
  			<td th:text="${#strings.endsWith(str, 'World!')}"></td>
  		</tr>
  		<tr>
  			<td>${#strings.indexOf(str, 'World!')}</td>	
  			<td th:text="${#strings.indexOf(str, 'World!')}"></td>
  		</tr>
  		<tr>
  			<td>${#strings.length(str)}</td>	
  			<td th:text="${#strings.length(str)}"></td>
  		</tr>
  		<tr>
  			<td>${#strings.escapeXml(str)}</td>	
  			<td th:text="${#strings.escapeXml(str)}"></td>
  		</tr>
  		<tr>
  			<td>${#strings.replace(str, 'Hello', 'Hi')}</td>	
  			<td th:text="${#strings.replace(str, 'Hello', 'Hi')}"></td>
  		</tr>
  		<tr>
  			<td>${#strings.toLowerCase(str)}</td>	
  			<td th:text="${#strings.toLowerCase(str)}"></td>
  		</tr>
  		<tr>
  			<td>${#strings.toUpperCase(str)}</td>	
  			<td th:text="${#strings.toUpperCase(str)}"></td>
  		</tr>
  		<tr>
  			<td>${#strings.trim(str)}</td>	
  			<td th:text="${#strings.trim(str)}"></td>
  		</tr>
  		<tr>
  			<td>${#strings.substring(str, 7, 12)}</td>	
  			<td th:text="${#strings.substring(str, 7, 12)}"></td>
  			</tr>
  		<tr>
  			<td>${#strings.substringAfter(str, 'World')}</td>
  			<td th:text="${#strings.substringAfter(str, 'World')}"></td>
  		</tr>
  		<tr>
  			<td>${#strings.substringBefore(str, 'World')}</td>	
  			<td th:text="${#strings.substringBefore(str, 'World')}"></td>
  		</tr>
  		<tr>
  			<td>tokenStr:${#strings.listSplit(str,' ')}</td>
  			<td>	
  				<ul>
  					<li th:each="tokenStr:${#strings.listSplit(str,' ')}">[[${tokenStr}]]</li>
  				</ul>
  			</td>
  		</tr>
  		<tr>
  			<td>tokenStr:${#strings.listSplit(str,' ')}</td>
  			<td>	
  				<ul>
  					<li th:each="tokenStr:${#strings.listSplit(str,' ')}">[[${tokenStr}]]</li>
  				</ul>
  			</td>
  		</tr>
  		<tr>
  			<td>
  				<div>strArray=${#strings.listSplit(str,' ')}</div>
  				<div>${#strings.arrayJoin(strArray, '-')}</div>
  			</td>
  			<td>		
  				<div th:with="strArray=${#strings.listSplit(str,' ')}">
  					<p th:text="${#strings.arrayJoin(strArray, '-')}">join : ${#strings.join(strArray, "-")} </p>
  				</div>
  			</td>
  		</tr>
  	</table>
  </body>
  </html>
  ```

- [응답 화면]

  ![](./img/39.png) 

### 10-4.자바 8 java.time 관련 #temporals

- 자바 8 java.time 클래스 기능을 이용한다.

- [요청] http://localhost:8080/ch0810/home0401

- [컨트롤러 메서드]

  ```java
  @GetMapping("ch0810/home0201")
  public String home0201(Model model) {
    Date date = new Date();

    model.addAttribute("now", date);

    return "ch0810/home0201";
  }
  ```

- [뷰 파일]

  ```html
  <html xmlns:th="http://www.thymeleaf.org">

  <head>
  	<title>Home</title>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  </head>
  <body>
  <table border="1">
  	<tr>
  		<td>${now}</td>
  		<td th:text="${now}"></td>
  	</tr>
  	<tr>
  		<td>${#temporals.format(now, 'yyyy-MM-dd')}</td>
  		<td th:text="${#temporals.format(now, 'yyyy-MM-dd')}"></td>
  	</tr>
  	<tr>
  		<td>${#temporals.format(now, 'yyyy-MM-dd HH:mm:ss')}</td>	
  		<td th:text="${#temporals.format(now, 'yyyy-MM-dd HH:mm:ss')}"></td>
  	</tr>
  	<tr>
  		<td>${#temporals.format(now, 'a h:mm')}</td>
  		<td th:text="${#temporals.format(now, 'a h:mm')}"></td>
  	</tr>
  	<tr>
  		<td>${#temporals.format(now, 'z a h:mm')}</td>	
  		<td th:text="${#temporals.format(now, 'z a h:mm')}"></td>
  	</tr>
  	<tr>
  		<td>timeValue=${#temporals.createToday()}</td>	
  		<td>
  			<div th:with="timeValue=${#temporals.createToday()}">
  				<p>[[${timeValue}]]</p>
  			</div>
  		</td>
  	</tr>
  </table>
  </body>
  </html>
  ```

- [응답 화면]

  ![](./img/40.png)  

