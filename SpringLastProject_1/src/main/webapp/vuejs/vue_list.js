let recipeApp=Vue.createApp({
		data(){
			return{
				list:[],
				curpage:1,
				startPage:1,
				endPage:1,
				
			}
		},
		methods:{
		range(start, end){
			let arr=[]
			let len=end-start
			for(let i=0; i<=len; i++)
				{
				arr[i]=start
				start++
			}
		  return arr
		  }
		}
	}).mount('.container')