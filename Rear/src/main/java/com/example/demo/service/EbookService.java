package com.example.demo.service;

import com.example.demo.config.CodeConfig;
import com.example.demo.entity.Classification;
import com.example.demo.entity.Ebook;
import com.example.demo.mapper.EbookMapper;
import com.example.demo.partEntity.FullBook;
import com.example.demo.partEntity.QueryBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class EbookService {
    @Autowired
    private EbookMapper ebookMapper;

    @Autowired
    private ClassificationService classificationService;

    @Autowired
    private EbookFileService ebookFileService;


    //一些常用的字段
    private Integer index = 0;
    private Integer pageSize = 0;
    String bookName = "";
    String suffix = "";
    List<String> classifications;
    //查询过程中类和数据库参数的映射
    private Map<String ,Object> queryParaMap;


    private void InitQueryParaMap(){
        queryParaMap = new HashMap<>();
        queryParaMap.put("index", this.index);
        queryParaMap.put("pageSize", this.pageSize);
        queryParaMap.put("book_name", this.bookName);
        queryParaMap.put("suffix", this.suffix);
        queryParaMap.put("classification", this.classifications.get(0));
    }

    // 生成随机id
    private String GenerateID(Ebook ebook){
        String result = "";
        StringBuilder randomStr = new StringBuilder();
        String codes = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        Integer isRedundant = 1;    // 为1表示是重复的id
        while(isRedundant != 0) {
            for (int i = 0; i < Ebook.idLen; i++) {
                randomStr.append(codes.charAt(random.nextInt(62)));
            }
            result = randomStr.toString();
            randomStr.setLength(0);
            isRedundant = this.ebookMapper.JudgeIDUnique(result);
        }
        return result;
    }

    // 查询满足所有分类条件的书的信息
    private List<QueryBook> QueryBookWithAllClass(){
        // 按条件依次循环直至条件为空或者结果为空，先单独找符合第一个条件的，确认最大的结果集合
        // 此时list中的元素都满足name和suffix的筛选
        List<QueryBook> result  = ebookMapper.Query(queryParaMap);

        //不断从已有的集合中根据新的条件筛选
        for(int i = 1; i < classifications.size(); i++){
            Iterator<QueryBook> iter = result.iterator();
            while(iter.hasNext()){
                QueryBook tmpBook = iter.next();
                // 因为后缀名是单选的，这里name和suffix都是满足的
                Integer isSatisfied = ebookMapper.CountCertainCondtion(classifications.get(i), tmpBook.getId());
                // 如果这本电子书没有模糊满足这个条件，那么就删除
                if(isSatisfied == 0){
                    iter.remove();
                }
            }
            // 已经没有书籍能满足当前的条件组合了
            if(result.size() == 0 )   break;
        }
        return result;
    }

    // 查询一本书所有的类别
    private void QueryBookClasses(QueryBook iter){
        //获取这本书对应的所有的类别
        System.out.println(iter.getBookName());
        List<String> classes = ebookMapper.Book2Class(iter.getId());
        //如果没有类别显示未知
        if(classes.size() == 0) {
            classes.add("未知");
        }
        //有类别用逗号进行拼接成一个字符串
        else {
            String tmpClasses = String.join(", ", classes);
            classes.clear();
            classes.add(tmpClasses);
        }
        iter.setClassifications(classes);
    }

    // 查询满足给定分类条件的所有书籍总数
    private Integer QueryTotalNumWithClass(){
        List<String> totalId = ebookMapper.QueryAll(queryParaMap);
        for(int i = 1; i < classifications.size(); i++){
            Iterator<String> iter = totalId.iterator();
            while(iter.hasNext()) {
                String tmpId = iter.next();
                Integer isSatisfied = ebookMapper.CountCertainCondtion(classifications.get(i), tmpId);
                if (isSatisfied == 0) {
                    iter.remove();
                }
            }
            if(totalId.size() == 0){
                break;
            }
        }
        return totalId.size();
    }

    // 构建新书和类别的映射关系
    private void MapClassifications(Ebook ebook){
        for(Classification iter: ebook.getClassifications()){
            ebookMapper.MapBookWithClassificaiton(ebook.getId(), iter.getId());
        }
    }

    //保存书到数据库
    private void SaveBook(Ebook ebook){
        Map<String, Object> map = new HashMap<>();
        map.put("book_id", ebook.getId());
        map.put("book_name", ebook.getBookName());
        map.put("author_name", ebook.getAuthorName());
        map.put("suffix", ebook.getSuffix());
        map.put("book_language", ebook.getLanguage());
        map.put("download", ebook.getDownload());
        map.put("introduction", ebook.getIntroduction());
        map.put("publisher", ebook.getPublisher());
        ebookMapper.InsertBook(map);
        this.MapClassifications(ebook);
    }




    // 根据给定的条件分页查询对应的电子书的信息
    @Transactional
    public  Map<String, Object> QueryBook(Integer pageNum, Integer pageSize,
                                          String bookName, List<String> classifications, String suffix) {

        this.index = (pageNum - 1) * pageSize;
        //名字对应模糊匹配
        this.bookName = "%" + bookName + "%";
        this.pageSize = pageSize;
        this.suffix = suffix;
        //如果没有条件自己添加一个""
        this.classifications = classifications;
        if(this.classifications.size() == 0)
            this.classifications.add("");
        //类别对应模糊匹配
        for(int i = 0; i < this.classifications.size(); i++){
            String tmp = this.classifications.get(i);
            this.classifications.set(i,"%" + tmp + "%");
        }

        this.InitQueryParaMap();
        List<QueryBook> list = this.QueryBookWithAllClass();
        for(QueryBook iter: list) {
            this.QueryBookClasses(iter);
        }
        Integer total = this.QueryTotalNumWithClass();

        // 将结果返回给前端
        Map<String, Object> result = new HashMap<>();
        result.put("code", CodeConfig.OK);
        result.put("data", list);
        result.put("total", total);
        System.out.println(list);
        return result;
    }

    // 查询已有的电子书涵盖的后缀名
    public Map<String, Object> GetAllSuffix(){
        Map<String, Object> result = new HashMap<>();
        List<String> list =  ebookMapper.GetAllSuffix();
        result.put("code", CodeConfig.OK);
        result.put("suffix", list);
        return result;
    }


    // 添加书籍，如果是同书名同作者则认为是一本书，不添加。 看用户验证会不会决定一本书是否有多种格式
    @Transactional
    public Map<String, Object> AddBook(Ebook ebook){
        System.out.println("找到了后台接口----------------------------------------");
        Map<String, Object> result = new HashMap<>();

        Integer isExistence = ebookMapper.CheckBook(ebook.getBookName(), ebook.getAuthorName());

        //同名同作者的书籍只有作者本人可以改或者上传其它格式
        if(isExistence == 1){
            result.put("code", CodeConfig.OK);
            result.put("data", null);
            return result;
        }

        // 新的书则生成新的id
        ebook.setId(this.GenerateID(ebook));

        // 维护类别
        for(Classification iter: ebook.getClassifications()){
            iter.setId(classificationService.GetAndCreateId(iter.getName()));
        }
        SaveBook(ebook);
        result.put("code", CodeConfig.OK);
        result.put("data",ebook);
        return result;
    }


    // 获得一本书的详细信息
    public Map<String, Object> GetABookInfo(String id){
        Map<String, Object> result = new HashMap<>();
        FullBook bookData = ebookMapper.GetABookInfo(id);
        this.QueryBookClasses(bookData);
        result.put("code", CodeConfig.OK);
        result.put("data", bookData);
        return result;
    }

    //删除ID指定的书籍
    @Transactional
    public void DeleteABook(String id){
        List<Integer> affectedClasses = ebookMapper.Book2ClassId(id);

        ebookMapper.DeleteClassMap(id);

        //如果一个类别没有对应的书籍，那么就删除这个类别
        classificationService.UpdateClass(affectedClasses);

        //找到要删除的文件名 删除ebook和file对应的关系
        ebookFileService.DeleteFileAndDB(id);

        //删除书本身的记录
        ebookMapper.DeleteBook(id);
    }

    //获得一本书的url
    public Map<String, Object>GetBookUrl(String bookId){
        Map<String, Object> result = new HashMap<>();
        String url = ebookFileService.SelectByBookId(bookId);
        result.put("code", CodeConfig.OK);
        result.put("data", url);
        return result;
    }

    public Map<String, Object> UpdateDownload(String bookId){
        Map<String, Object> result = new HashMap<>();
        ebookMapper.UpdateDownload(bookId);
        result.put("code", CodeConfig.OK);
        return result;
    }

    //测试用
    public Map<String, Object> QueryAll(){
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> list = ebookMapper.All();
        result.put("code", CodeConfig.OK);
        result.put("data", list);
        return result;
    }
}
