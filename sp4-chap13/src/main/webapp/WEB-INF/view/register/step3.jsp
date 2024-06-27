<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="member.register"/></title>
</head>
<body><!-- 뷰 코드는 클래스 이름을 사용해서 커맨드 객체에 접근 Controller 확인  -->
    <%-- <h2><strong>${registerRequest.name }님</strong> --%>
     <p>
         <spring:message code="register.done"
          arguments="${RegisterRequest.name }"/>님
     </p>
     <p><a href="<c:url value='/main'/>">[<spring:message code="go.main"/>]</a></p>
</body>
</html>
