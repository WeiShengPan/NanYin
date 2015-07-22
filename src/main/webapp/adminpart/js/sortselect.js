/**
 * Created by Administrator on 2015/7/22.
 */
$(document).ready(function(){
    $(".sidebar-menu li.treeview>a:contains(新闻管理)").parent().addClass("active");
    $("#sortbox").change(function(){
        var value=$(this).children('option:selected').val();
        $.get("/nyadmin/newslistbysort/"+value,function(data){

            var newslist=data;
            $("#newslistbysort").html("");
            $.each(newslist,function(i,news)
            {
                var name=news.newsSort.name;
                var title=news.title;
                var date=news.addDate;
                var id=news.id;
                $("#newslistbysort").append("<tr><td>"+name+"</td><td>"+title+"</td><td>"+date+"</td><td><a href=\"+newseditpage?id="+id+"\">修改 </a><a href=\"newsdelete?id="+id+"\">删除</a></td> </tr>");
            })
        },"json")
    });
});
