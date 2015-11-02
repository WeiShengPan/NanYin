package cn.nanyin.controller;

import cn.nanyin.adminauth.AdminAuthority;
import cn.nanyin.adminauth.AuthorityType;
import cn.nanyin.dao.ProductDao;
import cn.nanyin.model.Product;
import cn.nanyin.model.ProductSort;
import cn.nanyin.util.FileUpload;
import cn.nanyin.util.FileUploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

/**
 * Created by gg on 2015/7/20.
 */
@Controller
@AdminAuthority(authorityTypes = AuthorityType.PRODUCT_MANAGEMENT)
public class ProductController {
    @Autowired
    private ProductDao productDao;

    //显示商城产品列表页面
    @RequestMapping(value="nyadmin/productlist",method= RequestMethod.GET)
    public ModelAndView showProductList() {
        ModelAndView model = new ModelAndView("nyadmin/productlist");
        List<Product> productList=productDao.getProductList(0, 50);
        model.addObject("productList",productList);
        return model;
    }

    //显示增加产品页面
    @RequestMapping(value="nyadmin/productaddpage",method=RequestMethod.GET)
    public ModelAndView showProductAddPage() {
        ModelAndView model=new ModelAndView("nyadmin/productadd");
        List<ProductSort> productSortList=productDao.getProductSortList(0, 50);
        model.addObject("productSortList",productSortList);
        return model;
    }

    //添加产品
    @RequestMapping(value="nyadmin/productadd",method = RequestMethod.POST)
    public ModelAndView addProduct(Product product,BindingResult result) {
        product.setAddDate(new Date());
        productDao.addProduct(product);
        return new ModelAndView("redirect:productaddpage");
    }

    //显示产品种类列表页面
    @RequestMapping(value="nyadmin/productsort",method=RequestMethod.GET)
    public ModelAndView showProductSortList() {
        ModelAndView model=new ModelAndView("nyadmin/productsort");
        List<ProductSort> productSortList=productDao.getProductSortList(0, 50);
        model.addObject("productSortList", productSortList);
        return model;
    }

    //增加产品种类
    @RequestMapping(value="nyadmin/productsortadd",method=RequestMethod.POST)
    public ModelAndView addProductSort(ProductSort productSort) {
        int level=productDao.getProductSortById(productSort.getUpperProductSort().getId()).getLevel();
        productSort.setLevel(level + 1);
        productDao.addProductSort(productSort);
        return new ModelAndView("redirect:productsort");
    }

    //删除产品
    @RequestMapping(value="nyadmin/productdelete",method = RequestMethod.GET)
    public ModelAndView deleteProduct(long id) {
        Product product = productDao.getProductById(id);
        productDao.deleteProduct(product);
        return new ModelAndView("redirect:productlist");
    }

    //删除产品种类
    @RequestMapping(value="nyadmin/productsortdelete",method = RequestMethod.GET)
    public ModelAndView deleteProductSort(long id) {
        ProductSort productSort=productDao.getProductSortById(id);

        //级联删除所有该种类产品
        List<Product> productList=productSort.getProducts();
        for(int i=0;i<productList.size();i++) {
            Product productTmp1 =productList.get(i);
            productSort.removeProduct(productTmp1);
            productTmp1.setProductSort(null);
            productDao.updateProduct(productTmp1);
            productDao.deleteProduct(productTmp1);
        }

        //级联删除所有下层产品种类
        List<ProductSort> lowerProductSortList=productSort.getLowerProductSortList();
        for(int i=0;i<lowerProductSortList.size();i++)
        {
            ProductSort productSortTmp=lowerProductSortList.get(i);
            List<Product> lowerProdutList=productSortTmp.getProducts();
            for(int j=0;j<lowerProdutList.size();j++)
            {
                Product productTmp2=lowerProdutList.get(i);
                productSortTmp.removeProduct(productTmp2);
                productTmp2.setProductSort(null);
                productDao.updateProduct(productTmp2);
                productDao.deleteProduct(productTmp2);
            }
            productSort.removeProductSort(productSortTmp);
            productSortTmp.setUpperProductSort(null);
            productDao.updateProductSort(productSortTmp);
            productDao.deleteProductSort(productSortTmp);
        }

        productDao.deleteProductSort(productSort);
        return new ModelAndView("redirect:productsort");
    }

    //显示修改产品页面
    @RequestMapping(value="nyadmin/producteditpage",method = RequestMethod.GET)
    public ModelAndView showProductEditPage(long id) {
        ModelAndView model=new ModelAndView("nyadmin/productedit");
        Product product=productDao.getProductById(id);
        model.addObject("product", product);
        List<ProductSort> productSortList=productDao.getProductSortList(0, 50);
        model.addObject("productSortList", productSortList);
        return model;
    }

    //修改产品
    @RequestMapping(value="nyadmin/productedit",method = RequestMethod.POST)
    public ModelAndView editProduct(Product product) {
        Product targetProduct=productDao.getProductById(product.getId());
        targetProduct.setName(product.getName());
        targetProduct.setFile(product.getFile());
        targetProduct.setContent(product.getContent());
        targetProduct.setPrice(product.getPrice());
        targetProduct.setLink(product.getLink());
        targetProduct.setRecommendation(productDao.getProductById(product.getId()).getRecommendation());
        targetProduct.setProductSort(productDao.getProductSortById(product.getProductSort().getId()));
        targetProduct.setAddDate(productDao.getProductById(product.getId()).getAddDate());
        targetProduct.setHits(productDao.getProductById(product.getId()).getHits());
        targetProduct.setState(productDao.getProductById(product.getId()).getState());
        productDao.updateProduct(targetProduct);
        return new ModelAndView("redirect:productlist");
    }

    //显示修改产品类别页面
    @RequestMapping(value="nyadmin/productsorteditpage",method = RequestMethod.GET)
    public ModelAndView showProductSortEditPage(long id) {
        ModelAndView model=new ModelAndView("nyadmin/productsortedit");
        ProductSort productSort=productDao.getProductSortById(id);
        model.addObject("productSort", productSort);
        List<ProductSort> productSortList=productDao.getProductSortList(0, 50);
        model.addObject("productSortList", productSortList);
        return model;
    }

    //修改产品类别
    @RequestMapping(value="nyadmin/productsortedit",method = RequestMethod.POST)
    public ModelAndView editProductSort(ProductSort productSort) {
        ProductSort targetProductSort=productDao.getProductSortById(productSort.getId());
        targetProductSort.setName(productSort.getName());
        targetProductSort.setUpperProductSort(productDao.getProductSortById(productSort.getUpperProductSort().getId()));
        targetProductSort.setLevel(productDao.getProductSortById(productSort.getId()).getLevel());
        productDao.updateProductSort(targetProductSort);
        return new ModelAndView("redirect:productsort");
    }

    /**
     * 上传文件
     * @param file
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "nyadmin/productimage", method = RequestMethod.POST)
    public FileUploadResult uploadImage(@RequestParam MultipartFile file, HttpSession session) {
        String basePath = "/upload/product/image/";
        FileUpload fileUpload=new FileUpload(basePath,file,session);
        return fileUpload.upload();
    }

    @ResponseBody
    @RequestMapping(value = "nyadmin/ckeditorproductimage", method = RequestMethod.POST)
    public String uploadCkeditorImage(@RequestParam MultipartFile upload, HttpSession session,HttpServletResponse response,HttpServletRequest request) {
        PrintWriter out= null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String basePath = "/upload/product/image/";
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
