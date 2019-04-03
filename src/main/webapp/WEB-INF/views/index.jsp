<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:main>
    <jsp:attribute name="title">Test portal.</jsp:attribute>
    <jsp:body>
        <div class="top-margin">
            <h3>Please take a few minutes and leave a comment.</h3>
        </div>

        <div class="scroll-box-layer">
            <table class="aui">
                <thead>
                </thead>
                <tbody id="comment-list">
                <c:forEach items="${commentList}" var="comment">
                    <tr>
                        <td id="${comment.id}-comment-date">
                            <div>
                                    ${comment.date}
                            </div>
                            <div>
                                    ${comment.comment}
                            </div>

                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="content">
            <div class="comments">
                <div class="editor">
                    <div class="editor-header">
                        <a href='#' data-role='bold'>B</a>
                        <a href='#' data-role='italic'>I</a>
                        <a href='#' data-role='underline'>U</a>
                        <a href='#' data-role='justifyleft'><i class="menu-left"></i></a>
                        <a href='#' data-role='justifycenter'><i class="menu-center"></i></a>
                        <a href='#' data-role='justifyright'><i class="menu-right"></i></a>
                    </div>
                    <div id="text" class="editor-content" contenteditable>
                        <p>Enter comment here.</p>
                    </div>
                </div>
                <div class="insert-text">
                    <p>
                        <input type="submit" value="Comment"/>
                    </p>
                </div>
            </div>
        </div>
    </jsp:body>
</t:main>

