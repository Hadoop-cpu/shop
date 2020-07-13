package com.zcib.domain;

import java.util.List;
import java.util.Map;

public class Page {
		private int pageSize = 4;//一个页面显示几条记录,规定的
		private int totalSize;//共有多少条记录，从数据库读取的
		private int currentPage;//当前页码，页面传递过来的
//		private int totalPage;//共有多少页，只读
	
		private List<Map<String,Object>> list;//当前页所有记录
//		private int startIndex;//读取记录的起始索引值，只读
		private int num = 6;//页码列表显示页码个数,规定的
//		private int start;//页码列表的起始值，只读
//		private int end;//页码列表的终止值，只读
		
		//构造方法
		public Page(int currentPage,int totalSize){
			this.totalSize = totalSize;
			setCurrentPage(currentPage);
		}
		
		public int getPageSize() {
			return pageSize;
		}
		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}
		public int getTotalSize() {
			return totalSize;
		}
		public void setTotalSize(int totalSize) {
			this.totalSize = totalSize;
		}
		public int getCurrentPage() {
			return currentPage;
		}
		public void setCurrentPage(int currentPage) {
			if(currentPage<1){ //当前页码小于1
				this.currentPage = 1;
				return;
			}
			int totalPage = getTotalPage();//获取总页数
			if(currentPage>totalPage){//当前页码大于总页数
				this.currentPage = totalPage;
				return;
			}
			this.currentPage = currentPage;
			
		}
		public int getTotalPage() {
			return (totalSize%pageSize==0)?(totalSize/pageSize):(totalSize/pageSize+1);
		}
		
		public List<Map<String, Object>> getList() {
			return list;
		}
		public void setList(List<Map<String, Object>> list) {
			this.list = list;
		}
		
		public int getStartIndex() {
			int startIndex = (currentPage-1)*pageSize;
			if(startIndex<0)
				startIndex = 0;
			return startIndex;
		}
		
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}
		public int getStart() {
			//（1）总页码数totalPage>num
			int totalPage = getTotalPage();
			int start = 1;
			if(totalPage > num){
				//如果当前页是1，2，3的时候，页码列表显示1-6
				if(currentPage <= num/2){
					start =1;
				}else 	if(currentPage > totalPage - num/2 + 1 ){
					//如果当前页是currentPage > totalPage-2，也就是当前页在最后几个
					start = totalPage - num +1;
				}else{
					start =currentPage-num/2;
				}
			}else{ //总页码数totalPage小于规定显示的页码个数num
				start = 1;
			}
			return start;
		}

		public int getEnd() {
			int totalPage = getTotalPage();
			int end = 1;
			//（1）总页码数totalPage>num
			if(totalPage > num){
				//如果当前页是1，2，3的时候，页码列表显示1-6
				if(currentPage <= num/2){
					end = 6;
				}else 	if(currentPage > totalPage - num/2 + 1 ){
					//如果当前页是currentPage > totalPage-2，也就是当前页在最后几个
					end = totalPage;
				}else{
					end = currentPage+num/2-1;
				}
			}else{ //总页码数totalPage小于规定显示的页码个数num
				end = totalPage;
			}
			return end;
		}

		@Override
		public String toString() {
			return "Page [pageSize=" + pageSize + ", totalSize=" + totalSize
					+ ", currentPage=" + currentPage + ", list=" + list
					+ ", num=" + num + "]";
		}

}
