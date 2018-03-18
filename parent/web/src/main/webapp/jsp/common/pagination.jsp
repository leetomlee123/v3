<%--
  Created by IntelliJ IDEA.
  User: 77558
  Date: 2017/9/30
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>


<!--分页 -->
<div style="width: 380px; margin: 0 auto; margin-top: 50px;">
    <ul class="pagination" style="text-align: center; margin-top: 10px;">

        <!-- 上一页 -->

        <c:if test="${pageBean.currentPage==1 }">
            <li class="disabled">
                <a href="javascript:void(0);" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
        </c:if>
        <c:if test="${pageBean.currentPage!=1 }">
            <li>
                <a href="${basePath}productList?cid=${cid}&currentPage=${pageBean.currentPage-1 }"
                   aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
        </c:if>


        <!-- 显示每一页 -->
        <c:forEach begin="1" end="${pageBean.totalPage }" var="page">
            <!-- 判断是否是当前页 -->
            <c:if test="${page==pageBean.currentPage }">
                <li class="active"><a href="javascript:void(0);">${page }</a></li>
            </c:if>
            <c:if test="${page!=pageBean.currentPage }">
                <li>
                    <a href="${basePath}productList?cid=${cid}&currentPage=${page }">${page}</a>
                </li>
            </c:if>
        </c:forEach>


        <!-- 下一页 -->
        <c:if test="${pageBean.currentPage==pageBean.totalPage }">
            <li class="disabled">
                <a href="javascript:void(0);" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </c:if>
        <c:if test="${pageBean.currentPage!=pageBean.totalPage }">
            <li>
                <a href="${basePath}productList?cid=${cid}&currentPage=${pageBean.currentPage+1 }"
                   aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </c:if>

    </ul>
</div>
<!-- 分页结束 -->
