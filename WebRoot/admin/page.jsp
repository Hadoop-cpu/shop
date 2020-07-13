<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="pagin">
			<div class="message">
				共<i class="blue">${page.totalSize}</i>条记录，当前显示第&nbsp;<i class="blue">${page.currentPage}&nbsp;</i>页
			</div>
			<ul class="paginList">
			
				<c:choose>
					<c:when test="${page.currentPage == 1}">
					<li class="paginItem"><a href="javascript:;"><span
						class="pagepre"></span>
					</a>
					</li>
					</c:when>
					<c:otherwise>
					<li class="paginItem"><a href="${url}&current=${page.currentPage - 1}&key=${key}&keytype=${keytype}"><span
						class="pagepre"></span>
					</a>
					</li>
					</c:otherwise>
				
				</c:choose>
				
				
				<c:forEach  var="i" begin="${page.start}" end="${page.end}">
					<c:choose>
						<c:when test="${page.currentPage == i }">
						<li class="paginItem current"><a href="javascript:;">${i}</a>
						</li>
						</c:when>
						<c:otherwise>
						<li class="paginItem"><a href="${url}&current=${i}&key=${key}&keytype=${keytype}">${i}</a>
						</li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				
				<c:choose>
					<c:when test="${page.currentPage == page.totalPage}">
					<li class="paginItem"><a href="javascript:;"><span
						class="pagenxt"></span>
					</a>
					</li>
					</c:when>
					<c:otherwise>
					<li class="paginItem"><a href="${url}&current=${page.currentPage + 1}&key=${key}&keytype=${keytype}"><span
						class="pagenxt"></span>
					</a>
					</li>
					</c:otherwise>
				
				</c:choose>
				
			</ul>
		</div>