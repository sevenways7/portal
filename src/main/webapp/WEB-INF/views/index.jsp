<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:main>
    <jsp:attribute name="title">Test portal.</jsp:attribute>
    <jsp:body>
        <%--Form don't need here, but otherwise
        "form .aui" selector not triggers--%>
        <form class="aui">
            <!-- Manage post dialog-->
            <div id="edit-delete-post-dialog" class="edit-delete-post-modal">
                <!-- Modal content -->
                <div class="edit-delete-post-modal-content">
                    <h3>
                        <div id="post-dialog-header" class="dialog-header-padding">Edit.</div>
                    </h3>

                    <div>
                        <textarea id="post-text" class="textarea text-area-settings"></textarea>
                    </div>

                    <input type="hidden" id="post-id" value="">

                    <div class="button-container">
                        <span>
                            <div id="save-post-dialog" class="aui-button edit-delete-post-modal-close">Save</div>
                            <div id="delete-post-dialog" class="aui-button edit-delete-post-modal-close">Delete</div>
                            <div id="close-post-dialog" class="aui-button edit-delete-post-modal-close">Close</div>
                        </span>
                    </div>
                </div>
            </div>

            <!-- Manage comment dialog-->
            <div id="edit-delete-comment-dialog" class="edit-delete-post-modal">
                <!-- Modal content -->
                <div class="edit-delete-post-modal-content">
                    <h3>
                        <div id="comment-dialog-header" class="dialog-header-padding">Edit.</div>
                    </h3>

                    <div>
                        <textarea id="comment-text" class="textarea text-area-settings"></textarea>
                    </div>

                    <input type="hidden" id="post-comment-id" post-id="" comment-id="" value="">

                    <div class="button-container">
                        <span>
                            <div id="save-comment-dialog" class="aui-button edit-delete-post-modal-close">Save</div>
                            <div id="delete-comment-dialog" class="aui-button edit-delete-post-modal-close">Delete</div>
                            <div id="close-comment-dialog" class="aui-button edit-delete-post-modal-close">Close</div>
                        </span>
                    </div>
                </div>
            </div>

            <div class="aui post-comment-container">
                <div class="create-post-button-container">
                    <div id="create-post-main-window" class="aui-button">Create post.</div>
                </div>

                <c:set var="postCount" value="${0}"/>
                <c:forEach items="${postList}" var="postItem">
                    <c:set var="postCount" value="${postCount+1}"/>
                    <div><P>${postCount}.</P></div>

                    <div class="post-container">
                        <div class="post-border">
                            <div id="posted-text-${postItem.id}" class="manage-post">
                                <p>${postItem.text}</p>
                            </div>
                        </div>

                        <div class="add-post-button-container">
                            <div id="add-comment-${postItem.id}" class="add-comment-to-post aui-button">Add
                                comment.
                            </div>
                        </div>
                    </div>

                    <div class="comment-container">
                        <c:forEach items="${postItem.comments}" var="commentItem">
                            <div class="comment-border">
                                <div class="manage-comment"
                                     id="commented-text-${postItem.id}-${commentItem.id}">
                                    <p>${commentItem.text}</p>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:forEach>
            </div>
        </form>
    </jsp:body>
</t:main>

