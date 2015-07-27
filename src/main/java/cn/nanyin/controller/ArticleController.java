package cn.nanyin.controller;

import cn.nanyin.dao.ArticleDao;
import cn.nanyin.model.Book;
import cn.nanyin.model.BookSort;
import cn.nanyin.util.FileUpload;
import cn.nanyin.util.FileUploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/7/20.
 */
//图文管理类
@Controller
public class ArticleController {

    @Autowired
    private ArticleDao articleDao;

    //显示文章列表页面
    @RequestMapping(value = "nyadmin/articlelist", method = RequestMethod.GET)
    public ModelAndView showBookList() {
        ModelAndView model = new ModelAndView("nyadmin/articlelist");
        List<Book> bookList = articleDao.getBookList(0, 50);
        List<BookSort> articleSortList = articleDao.getNewsSortList(0, 50);
        model.addObject("articleSortList", articleSortList);
        model.addObject("bookList", bookList);
        return model;
    }

    //显示增加新闻页面
    @RequestMapping(value = "nyadmin/articleaddpage", method = RequestMethod.GET)
    public ModelAndView showBookAddPage() {
        ModelAndView model = new ModelAndView("nyadmin/articleadd");
        List<BookSort> bookSortList = articleDao.getNewsSortList(0, 50);
        model.addObject("bookSortList", bookSortList);
        return model;
    }

    @ResponseBody
    @RequestMapping(value = "nyadmin/articlelistbysort/{sortid}", method = RequestMethod.GET)
    public List<BookData> getArticleListBySort(@PathVariable Long sortid) {
        BookSort bookSort = articleDao.getBookSortById(sortid);
        List<Book> bookList = bookSort.getBooks();
        List<BookData> bookDataList = new ArrayList<BookData>();
        for (int i = 0; i < bookList.size(); i++) {
            Book bookTmp = bookList.get(i);
            long id = bookTmp.getId();
            String title = bookTmp.getTitle();
            Date addDate = bookTmp.getAddDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = sdf.format(addDate);
            BookData bookData = new BookData(id, title, date, bookSort.getName());
            bookDataList.add(bookData);
        }
        return bookDataList;
    }

    //添加新闻
    @RequestMapping(value = "nyadmin/articleadd", method = RequestMethod.POST)
    public ModelAndView addBook(Book book, BindingResult result) {
        book.setAddDate(new Date());
        articleDao.addBook(book);
        return new ModelAndView("redirect:articleaddpage");
    }

    //显示新闻种类列表页面
    @RequestMapping(value = "nyadmin/articlesort", method = RequestMethod.GET)
    public ModelAndView showBookSortList() {
        ModelAndView model = new ModelAndView("nyadmin/articlesort");

        List<BookSort> bookSortList = articleDao.getNewsSortList(0, 50);
        model.addObject("bookSortList", bookSortList);
        return model;
    }

    //增加新闻种类
    @RequestMapping(value = "nyadmin/articlesortadd", method = RequestMethod.POST)
    public ModelAndView addBookSort(BookSort bookSort) {
        //if(newsSort.getUpperNewsSort().getId()!=1)
        int level = articleDao.getBookSortById(bookSort.getUpperBookSort().getId()).getLevel();
        bookSort.setLevel(level + 1);
        articleDao.addBookSort(bookSort);
        return new ModelAndView("redirect:articlesort");
    }

    //删除新闻
    @RequestMapping(value = "nyadmin/articledelete", method = RequestMethod.GET)
    public ModelAndView deleteBook(long id) {
        Book book = articleDao.getBookById(id);
        articleDao.deleteBook(book);
        return new ModelAndView("redirect:articlelist");
    }

    //删除新闻种类
    @RequestMapping(value = "nyadmin/articlesortdelete", method = RequestMethod.GET)
    public ModelAndView deleteBookSort(long id) {
        BookSort bookSort = articleDao.getBookSortById(id);

        //级联删除所有该种类新闻
        List<Book> bookList = bookSort.getBooks();
        for (int i = 0; i < bookList.size(); i++) {
            Book bookTmp1 = bookList.get(i);
            bookSort.removeBook(bookTmp1);
            bookTmp1.setBookSort(null);
            articleDao.updateBook(bookTmp1);
            articleDao.deleteBook(bookTmp1);
        }

        //级联删除所有下层新闻种类
        List<BookSort> lowerBookSortList = bookSort.getLowerBookSortList();
        for (int i = 0; i < lowerBookSortList.size(); i++) {
            BookSort bookSortTmp = lowerBookSortList.get(i);
            List<Book> lowerBookList = bookSortTmp.getBooks();
            for (int j = 0; j < lowerBookList.size(); j++) {
                Book bookTmp2 = lowerBookList.get(i);
                bookSortTmp.removeBook(bookTmp2);
                bookTmp2.setBookSort(null);
                articleDao.updateBook(bookTmp2);
                articleDao.deleteBook(bookTmp2);
            }
            bookSort.removeBookSort(bookSortTmp);
            bookSortTmp.setUpperBookSort(null);
            articleDao.updateBookSort(bookSortTmp);
            articleDao.deleteBookSort(bookSortTmp);
        }
        articleDao.deleteBookSort(bookSort);
        return new ModelAndView("redirect:articlesort");
    }

    //显示修改新闻页面
    @RequestMapping(value = "nyadmin/articleeditpage", method = RequestMethod.GET)
    public ModelAndView showBookEditPage(long id) {
        ModelAndView model = new ModelAndView("nyadmin/articleedit");
        Book book = articleDao.getBookById(id);
        model.addObject("book", book);
        List<BookSort> bookSortList = articleDao.getNewsSortList(0, 50);
        model.addObject("bookSortList", bookSortList);
        return model;
    }

    //修改新闻
    @RequestMapping(value = "nyadmin/articleedit", method = RequestMethod.POST)
    public ModelAndView editBook(Book book) {
        Book targetBook = articleDao.getBookById(book.getId());
        targetBook.setTitle(book.getTitle());
        targetBook.setAuthor(book.getAuthor());
        targetBook.setSource(book.getSource());
        targetBook.setFile(book.getFile());
        targetBook.setContent(book.getContent());
        targetBook.setBookSort(articleDao.getBookSortById(book.getBookSort().getId()));
        targetBook.setAddDate(articleDao.getBookById(book.getId()).getAddDate());
        targetBook.setHits(articleDao.getBookById(book.getId()).getHits());
        articleDao.updateBook(targetBook);
        return new ModelAndView("redirect:articlelist");
    }


    @RequestMapping(value = "nyadmin/articlesorteditpage", method = RequestMethod.GET)
    public ModelAndView showBookSortEditPage(long id) {
        ModelAndView model = new ModelAndView("nyadmin/articlesortedit");
        BookSort bookSort = articleDao.getBookSortById(id);
        model.addObject("bookSort", bookSort);
        List<BookSort> bookSortList = articleDao.getNewsSortList(0, 50);
        model.addObject("bookSortList", bookSortList);
        return model;
    }

    @RequestMapping(value = "nyadmin/articlesortedit", method = RequestMethod.POST)
    public ModelAndView editBookSort(BookSort bookSort) {
        BookSort targetBookSort = articleDao.getBookSortById(bookSort.getId());
        targetBookSort.setName(bookSort.getName());
        targetBookSort.setUpperBookSort(articleDao.getBookSortById(bookSort.getUpperBookSort().getId()));
        targetBookSort.setLevel(articleDao.getBookSortById(bookSort.getId()).getLevel());
        articleDao.updateBookSort(targetBookSort);
        return new ModelAndView("redirect:articlesort");
    }

    /**
     *
     * @param file
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "nyadmin/articleimage", method = RequestMethod.POST)
    public FileUploadResult uploadImage(@RequestParam MultipartFile file, HttpSession session) {

        String basePath="/upload/article/image/";
        FileUpload fileUpload=new FileUpload(basePath,file,session);
        return fileUpload.upload();
    }

    @ResponseBody
    @RequestMapping(value = "nyadmin/ckeditorarticleimage", method = RequestMethod.POST)
    public String uploadCkeditorImage(@RequestParam MultipartFile upload, HttpSession session,HttpServletResponse response,HttpServletRequest request)
    {
        PrintWriter out= null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String basePath = "/upload/article/image/";
        FileUpload fileUpload=new FileUpload(basePath,upload,session);
        FileUploadResult fileUploadResult=fileUpload.upload();

        String callback = request.getParameter("CKEditorFuncNum");
        out.println("<script type=\"text/javascript\">");
        out.println("window.parent.CKEDITOR.tools.callFunction("
                + callback + ",'" + fileUploadResult.getFileName() + "',''" + ")");
        out.println("</script>");

        return null;
    }
}

class BookData {
    public long id;
    public String title;
    public String date;
    public String sortName;

    public BookData(long id, String title, String date, String sortName) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.sortName = sortName;
    }

}