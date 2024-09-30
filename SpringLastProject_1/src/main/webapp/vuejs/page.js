const page_card={
			template:
			`<ul class="pagination">
				<li v-if="$parents.startPage>1"><a class="nav-link"  @click="prev()" >&lt;</a></li>
				<li v-for="i in range($parents.startPage, $parents.endPage)" :class="i===$parents.curpage?'active':''"><a class="nav-link" @click="pageChange(i)">{{i}}</a></li>
				<li v-if="$parents.endPage<$parents.totalPage"><a class="nav-link" @click="next()">&gt;</a></li>
			</ul>`
	}