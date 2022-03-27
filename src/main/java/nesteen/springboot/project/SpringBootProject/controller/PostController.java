package nesteen.springboot.project.SpringBootProject.controller;

import nesteen.springboot.project.SpringBootProject.entity.*;
import nesteen.springboot.project.SpringBootProject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;


@Controller
@RequestMapping("/post")
public class PostController {

    private PostService postService;
    private TagsService tagsService;
    private CommentsService commentsService;
    private PostTagsService postTagsService;
    private UserService userService;
    static int POST_ID=0;
    private int itrate = 0;
    private int prev=0;
    List<Post> fetchedData=new ArrayList<>();

    public PostController(PostService thePostService,
                          TagsService theTagsService,
                          CommentsService theCommentsService,
                          PostTagsService thePostTagsService,
                          UserService theUserService) {
        postService = thePostService;
        tagsService = theTagsService;
        commentsService=theCommentsService;
        postTagsService = thePostTagsService;
        userService=theUserService;
    }
   /* @PostMapping("/blog")
    public String totalBlog(Model theModel){
        List<Post> thePosts= postService.findAll();
        theModel.addAttribute("posts",thePosts);
        return "post";
    }*/
    @GetMapping("/blog")
    public String getAllPages(Model theModel){

        return getPage(theModel,1);
    }
    @GetMapping("/blog/page/{pageNumber}")
    public String getPage(Model theModel, @PathVariable("pageNumber") int currentPage){
        Page<Post> page=postService.findPage(currentPage);
        int totalPage= page.getTotalPages();
        long totalItem=page.getTotalElements();
        List<Post> posts= page.getContent();
        FilterData filterData=new FilterData();

        theModel.addAttribute("currentPage",currentPage);
        theModel.addAttribute("totalPage",totalPage);
        theModel.addAttribute("totalItem",totalItem);
        theModel.addAttribute("posts",posts);
        theModel.addAttribute("filterAuthorData",postService.findDistinctAuthor());
        theModel.addAttribute("filterTagData",tagsService.findAll());
        //int days = Days.daysBetween(LocalDate.now().plusDays(-3), LocalDate.now().plusDays(3)).getDays();
        List<LocalDate> dates = new ArrayList<LocalDate>();  // Set initial capacity to `days`.
        for (int i=-6; i < 1; i++) {
            //LocalDate d = startDate.withFieldAdded(DurationFieldType.days(), i);
            //dates.add(d);
            dates.add(LocalDate.now().plusDays(i));
        }
        theModel.addAttribute("filterDateData",dates);
        theModel.addAttribute("filterData",filterData);
        return "post";
    }

    @GetMapping("/search/page/{pageNumber}")
    public  String getSearch(Model theModel,
                            String keyword,
                            @PathVariable("pageNumber") int currentPage){    //@RequestParam(value = "keyword", required = false)
        if(keyword ==null) {
            Page<Post> page=postService.findPage(currentPage);
            int totalPage= page.getTotalPages();
            long totalItem=page.getTotalElements();
            List<Post> posts= page.getContent();
            FilterData filterData=new FilterData();

            theModel.addAttribute("currentPage",currentPage);
            theModel.addAttribute("totalPage",totalPage);
            theModel.addAttribute("totalItem",totalItem);
            theModel.addAttribute("posts",posts);
            theModel.addAttribute("filterAuthorData",postService.findDistinctAuthor());
            theModel.addAttribute("filterTagData",tagsService.findAll());
            //int days = Days.daysBetween(LocalDate.now().plusDays(-3), LocalDate.now().plusDays(3)).getDays();
            List<LocalDate> dates = new ArrayList<LocalDate>();  // Set initial capacity to `days`.
            for (int i=-6; i < 1; i++) {
                //LocalDate d = startDate.withFieldAdded(DurationFieldType.days(), i);
                //dates.add(d);
                dates.add(LocalDate.now().plusDays(i));
            }
            theModel.addAttribute("filterDateData",dates);
            theModel.addAttribute("filterData",filterData);
        }
        else {
            Page<Post> page=postService.findByKeyword(keyword,currentPage);
            int totalPage= page.getTotalPages();
            long totalItem=page.getTotalElements();
            List<Post> posts= page.getContent();
            FilterData filterData=new FilterData();

            theModel.addAttribute("currentPage",currentPage);
            theModel.addAttribute("totalPage",totalPage);
            theModel.addAttribute("totalItem",totalItem);
            theModel.addAttribute("posts",posts);

            theModel.addAttribute("filterAuthorData",postService.findDistinctAuthor());
            theModel.addAttribute("filterTagData",tagsService.findAll());
            //int days = Days.daysBetween(LocalDate.now().plusDays(-3), LocalDate.now().plusDays(3)).getDays();
            List<LocalDate> dates = new ArrayList<LocalDate>();  // Set initial capacity to `days`.
            for (int i=-6; i < 1; i++) {
                //LocalDate d = startDate.withFieldAdded(DurationFieldType.days(), i);
                //dates.add(d);
                dates.add(LocalDate.now().plusDays(i));
            }
            theModel.addAttribute("filterDateData",dates);
            theModel.addAttribute("filterData",filterData);

            //System.out.println("****************************** 1");
            //theModel.addAttribute("posts",postService.findByKeyword(keyword));
            //System.out.println("****************************** 1");
        }

        return "post";
    }
    @GetMapping("/sorted/page/{currentPage}/{field}")
    public String getAllWithSort(Model theModel,
                                 @PathVariable("field") String field,
                                 @PathVariable("currentPage") int currentPage) {
        Page<Post> pages=postService.findAllWithSort(field,currentPage);
        List<Post> posts=pages.getContent();
        int totalPage= pages.getTotalPages();
        long totalItem=pages.getTotalElements();
        FilterData filterData=new FilterData();

        theModel.addAttribute("currentPage",currentPage);
        theModel.addAttribute("totalPage",totalPage);
        theModel.addAttribute("totalItem",totalItem);
        theModel.addAttribute("posts",posts);

        theModel.addAttribute("filterAuthorData",postService.findDistinctAuthor());
        theModel.addAttribute("filterTagData",tagsService.findAll());
        //int days = Days.daysBetween(LocalDate.now().plusDays(-3), LocalDate.now().plusDays(3)).getDays();
        List<LocalDate> dates = new ArrayList<LocalDate>();  // Set initial capacity to `days`.
        for (int i=-6; i < 1; i++) {
            //LocalDate d = startDate.withFieldAdded(DurationFieldType.days(), i);
            //dates.add(d);
            dates.add(LocalDate.now().plusDays(i));
        }
        theModel.addAttribute("filterDateData",dates);
        theModel.addAttribute("filterData",filterData);


        return "post";
    }

   /* @GetMapping("/sort")
    public String getSortFirstPage(Model theModel){
        return getSortPage(theModel,1);
    }*/
//
    /*@GetMapping("/sort/{pageNumber}")
    public String getSortPage(Model theModel,@PathVariable("pageNumber") int currentPage){
        Page<Post> page=postService.findSortedPage(currentPage);
        int totalPage= page.getTotalPages();
        long totalItem=page.getTotalElements();
        List<Post> posts= page.getContent();

        theModel.addAttribute("currentPage",currentPage);
        theModel.addAttribute("totalPage",totalPage);
        theModel.addAttribute("totalItem",totalItem);
        theModel.addAttribute("posts",posts);
        return "post";
    }*/
    /*@GetMapping("/blog")
    public String test(Model theModel,
                       @RequestParam(value = "selectedButton", required = false) String selectedButton,
                       @RequestParam(value = "endPage", required = false) String endPage,
                       @RequestParam(value = "startPage", required = false) String startPage){
        List<Post> thePosts= postService.findAllSort();                          //findAll();
        List<Post> thePagedPost=new ArrayList<>();
        int totalItrate=thePosts.size()/3;
        int remainItrate=thePosts.size()%3;
        System.out.println(itrate);
        //if(itrate <thePosts.size()) {
            if (selectedButton == null) {
                if(prev==0)
                    itrate=0;
                for (int i = 0; i < 3; i++) {
                    thePagedPost.add(thePosts.get(i));
                    itrate++;
                    prev=0;
                }
            } else if (selectedButton.equals("next") && (thePosts.size() - itrate) >= 3) {
                if(prev==1)
                    itrate=itrate-3;
                int thisItrate=itrate+3;
                for (int i = itrate; i < thisItrate; i++) {
                    thePagedPost.add(thePosts.get(i));
                    itrate++;
                }
                prev=1;
            } else if (selectedButton.equals("next") && (thePosts.size() - itrate) < 3) {
                for (int i = itrate; i < thePosts.size(); i++) {
                    thePagedPost.add(thePosts.get(i));
                    itrate++;
                }
            }
            else if (selectedButton.equals("previous") && (itrate%3 != 0)){
                itrate=itrate-3-(itrate%3);
                int thisItrate=itrate+3;
                for (int i=itrate;i<thisItrate;i++){
                    thePagedPost.add(thePosts.get(i));
                    itrate++;
                }

            }
            else if (selectedButton.equals("previous") && itrate>=5) {
                int thisItrate=itrate-3;
                itrate=itrate-6;
                for (int i = itrate; itrate < thisItrate; i++) {
                    thePagedPost.add(thePosts.get(i));
                    itrate++;
                }
            }
        //}
        //else
            //thePagedPost.add(thePosts.get(3));
        //else if(selectedButton.equals("next") && itrate>=thePosts.size())
            //thePagedPost.add(thePosts.get(itrate));


        theModel.addAttribute("posts",thePagedPost);
        return "post";
    }*/
    @GetMapping("/login")
    public String login(){ //Model theModel
        //Post thePost=new Post();
        //theModel.addAttribute("post",thePost);
        return "login";
    }
    @GetMapping("/logout-success")
    public String logout(){
        return "login";
    }
    //,UserService theUserService
    @GetMapping("/create")
    public String showRegistrationForm(Model theModel){
        theModel.addAttribute("user",new User());
        return "register";
    }
    @PostMapping("/register")
    public String createAccount(@ModelAttribute("user") User user,Model theModel){
        //System.out.println(user);
        //Post thePost=new Post();
        //theModel.addAttribute("post",thePost);
        user.setRole("USER");
        userService.save(user);

        return "redirect:/post/login";
    }
    @GetMapping("/newPost")
    public String newPost(Model theModel){
        Post thePost=new Post();
        theModel.addAttribute("post",thePost);
        return "add-blog";
    }
    @PostMapping("/publish")
    public String publishPost(Model theModel,
                              @ModelAttribute("post") Post thePost
                              //@RequestParam(value = "tags", required = false) String tagString
                              ){
        //
        //
        //List<Tag> theTag= tagService.findAll();
        //System.out.println(theTag);
        //theModel.addAttribute("tags",theTag);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User currentUser=userService.findByEmail(currentPrincipalName);
        thePost.setAuthor(currentUser.getName());
        //System.out.println(currentUser.getName());
        String[] tags = thePost.getTag().split(" ");
        thePost.setExcerpt(thePost.getContent().substring(0,300)+" ");
        thePost.setPublishedAt(LocalDateTime.now());
        thePost.setCreatedAt(LocalDateTime.now());
        thePost.setUpdatedAt(LocalDate.now()+"");
        postService.save(thePost);
        int postId=thePost.getId();
        for(String tempTag:tags) {
            if(tagsService.findByNameIgnoreCaseLike(tempTag).size() == 0) {
                Tags tag = new Tags(tempTag, postId, LocalDateTime.now(), LocalDateTime.now());
                tagsService.save(tag);
                postTagsService.save(new PostTags(postId,tag.getId()));
            }
            else {
                List<Tags> tag =tagsService.findByNameIgnoreCaseLike(tempTag);
                postTagsService.save(new PostTags(postId,tag.get(0).getId()));
            }
        }
        //System.out.println(thePost);
        return "redirect:/post/blog";
    }
    @PostMapping("/update")
    public String update(@RequestParam("postId") int theId,Model theModel){
        Post thePost =postService.findById(theId);
        //String theTags=tagsService.findStatusById(theId);
        theModel.addAttribute("post",thePost);
        if(thePost.getTag()==null)
            thePost.setTag("#Default");
        String[] tags=thePost.getTag().split(" ");
        postTagsService.deleteByPostId(theId);
        for(String tempTag:tags){
            if(tagsService.findByNameIgnoreCaseLike(tempTag).size() == 0) {
                Tags tag = new Tags(tempTag, theId, LocalDateTime.now(), LocalDateTime.now());
                tagsService.save(tag);
                postTagsService.save(new PostTags(theId,tag.getId()));
            }
            else {
                List<Tags> tag =tagsService.findByNameIgnoreCaseLike(tempTag);
                postTagsService.save(new PostTags(theId,tag.get(0).getId()));
            }
        }
        //System.out.println(theTags);
        //theModel.addAttribute("tags",theTags);
        return "add-blog";
    }
    @PostMapping("/delete")
    public String delete(@RequestParam("postId") int theId) {
        postService.deleteById(theId);
        postTagsService.deleteByPostId(theId);
        commentsService.deletePerPostId(theId);
        return "redirect:/post/blog";

    }

    @GetMapping("/viewpost")
    public String viewPost(Model theModel,@RequestParam("postId") int theId){
        Post thePost =postService.findById(theId);
        List<PostTags> postTag=postTagsService.findByPostId(theId);
        String theTags="";
        for(PostTags tags:postTag) {
            theTags = tagsService.findTagById(tags.getTagId()).getName() + " " + theTags;
        }
        List<Comments> theComments=commentsService.findAllComments(theId);
        theModel.addAttribute("posts",thePost);
        theModel.addAttribute("tags",theTags);
        theModel.addAttribute("comments",theComments);
        theModel.addAttribute("theComment",new Comments());
        POST_ID=thePost.getId();
        return "view-post";
    }

    @GetMapping("/comment")
    public String comments(Model theModel,
                           //@RequestParam(value = "name", required = false) String name,
                           //@RequestParam(value = "email", required = false) String email,
                           //@RequestParam(value = "comment", required = false) String comment,
                           @ModelAttribute("theComment") Comments comments){
        //System.out.println(comments);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User currentUser=userService.findByEmail(currentPrincipalName);
        if(comments.getName()==null) {
            //Comments theComment = new Comments(currentUser.getName(), currentPrincipalName, comment, POST_ID, LocalDateTime.now(), LocalDateTime.now());
            //commentsService.save(theComment);
            comments.setName(currentUser.getName());
            comments.setEmail(currentPrincipalName);
            comments.setCreatedAt(LocalDateTime.now());
            comments.setUpdatedAt(LocalDateTime.now());
            comments.setPostId(POST_ID);
            commentsService.save(comments);
        }
       else {
            //Comments theComment = new Comments(name, email, comment, POST_ID, LocalDateTime.now(), LocalDateTime.now());
            comments.setCreatedAt(LocalDateTime.now());
            comments.setUpdatedAt(LocalDateTime.now());
            comments.setPostId(POST_ID);
            commentsService.save(comments);
            //commentsService.save(theComment);
        }


        return "redirect:/post/viewpost?postId="+(POST_ID);
    }

    @PostMapping("/updateComment")
    public String updateComment(Model theModel,
                                //@RequestParam(value = "name", required = false) String name,
                                //@RequestParam(value = "email", required = false) String email,
                                //@RequestParam(value = "comment", required = false) String comment,
                                @RequestParam("commentId") int theId){
        Comments theComments =commentsService.findById(theId);
        theModel.addAttribute("theComment",theComments);
        return "redirect:/post/viewpost?postId="+(POST_ID);
    }
    @PostMapping("/deleteComment")
    public String deleteComment(@RequestParam("postId") int theId) {
        commentsService.deleteById(theId);
        return "redirect:/post/viewpost?postId="+(POST_ID);

    }

    /*@GetMapping("/sort")
    public String sortPost(Model theModel){
        //check=1;
        System.out.println(fetchedData.size());
        if(fetchedData==null) {

            fetchedData= postService.findByOrderByPublishedAtAsc();
            System.out.println(fetchedData.size());
        }
        else{

        }
        List<Post> thePostsSort = postService.findByOrderByPublishedAtAsc();
        theModel.addAttribute("posts", thePostsSort);
        return "post";

    }*/

    @GetMapping("/filter/page/{pageNumber}")
    public String filterPost(Model theModel,
                             @ModelAttribute("filterData")  FilterData filterData,
                             //@RequestParam(value = "filterAuthorValue", required = false) String filterAuthorValue,
                             //@RequestParam(value = "filterDateValue", required = false) String filterDateValue,
                             @RequestParam(value = "filterAuthorValue", required = false) String filterValue,
                            @PathVariable("pageNumber") int currentPage) {
        List<Integer> postId = new ArrayList<>();
        List<String> tagSplit=new ArrayList<>();
        List<String> authorSplit=new ArrayList<>();
        List<String> dateSplit=new ArrayList<>();
        if ((filterData.getAuthor() == null) && (filterData.getDate() == null) && (filterData.getTags() == null))
            return "redirect:/post/blog";
        if (filterData.getTags() != null) {
            tagSplit = List.of(filterData.getTags().split(","));
        }
            // List<Integer> postId = null;
        if (filterData.getAuthor() != null) {
            authorSplit = List.of(filterData.getAuthor().split(","));
        }
        if(filterData.getDate() != null) {
            dateSplit = List.of(filterData.getDate().split(","));
        }
        //System.out.println(tagSplit);
        //System.out.println(dateSplit);
        //System.out.println(authorSplit);
        for (String tag : tagSplit) {
            List<PostTags> thePosts = postTagsService.findByTagId(tagsService.findTagIdByName(tag));
            for (PostTags postTags : thePosts)
                postId.add(postTags.getPostId());
        }
        Page<Post> page = postService.findPageByAuthorName(authorSplit, postId, dateSplit, currentPage);
        //.findPage(currentPage);
        int totalPage = page.getTotalPages();
        long totalItem = page.getTotalElements();
        List<Post> posts = page.getContent();

        theModel.addAttribute("currentPage", currentPage);
        theModel.addAttribute("totalPage", totalPage);
        theModel.addAttribute("totalItem", totalItem);
        theModel.addAttribute("posts", posts);
        theModel.addAttribute("filterAuthorData", postService.findDistinctAuthor());
        theModel.addAttribute("filterTagData", tagsService.findAll());
                //int days = Days.daysBetween(LocalDate.now().plusDays(-3), LocalDate.now().plusDays(3)).getDays();
        List<LocalDate> dates = new ArrayList<LocalDate>();  // Set initial capacity to `days`.
        for (int i = -3; i < 4; i++) {
            //LocalDate d = startDate.withFieldAdded(DurationFieldType.days(), i);
            //dates.add(d);
            dates.add(LocalDate.now().plusDays(i));
        }

        theModel.addAttribute("filterDateData", dates);
        theModel.addAttribute("filterData", filterData);
        return "post";
                //if(authorSplit.size() > 1)
                //return "redirect:/post/blog";

            /*if (filterData.getDate() != null) {
                List<String> dateSplit = List.of(filterData.getDate().split(","));
                //if(dateSplit.length > 1)
                //return "redirect:/post/blog";
            }*/
            /*if (filterData.getTags() != null) {
                List<String> tagSplit = List.of(filterData.getTags().split(","));
                List<Integer> postId = null;
                for (String tag : tagSplit) {
                    List<PostTags> thePosts = postTagsService.findByTagId(tagsService.findTagIdByName(tag));
                    for (PostTags postTags : thePosts)
                        postId.add(postTags.getPostId());
                }*/


            //if(authorSplit.length > 1 || dateSplit.length > 1)
           // return "redirect:/post/blog";
        /*String[] filterValues=filterValue.split("_");
        if(Integer.parseInt(filterValues[0])==1){
            String theAuthor=filterValues[1]+" "+filterValues[2];
            List<Post> thePostsSort=postService.findListByAuthorName(theAuthor);
            theModel.addAttribute("posts", thePostsSort);
            return "post";
        }
        else if(Integer.parseInt(filterValues[0])==2){
            String publishDate=filterValues[1]+"%";
            List<Post> postByPublishDate=postService.findByPublishedAtLike(publishDate);
            theModel.addAttribute("posts", postByPublishDate);
            return "post";
        }
        //find by id
        else if(Integer.parseInt(filterValues[0])==3){
            String tagName="#"+filterValues[1];
            List<Tags> tagsList=tagsService.findTagByName(tagName);
            List<PostTags> postAndTag=postTagsService.findByTagId(tagsList.get(0).getId());
            List<Post> postByTags=new ArrayList<>();
            for(PostTags tag:postAndTag){
                postByTags.add(postService.findById(tag.getPostId()));
            }
            theModel.addAttribute("posts", postByTags);
            return "post";
        }
        else*/
            //return null;
        }

    /*@GetMapping("/search")
    public String searchPost(Model theModel,
                             @RequestParam(value = "search", required = false) String search) {
        List<Post> postBySearch=new ArrayList<>();
        if((postService.findByTitleIgnoreCaseLike(search+"%"))!=null){
            for(Post post:postService.findByTitleIgnoreCaseLike(search+"%"))
                postBySearch.add(post);
        }
        if (postService.findByContentIgnoreCaseLike(search+"%") != null) {
            for(Post post:postService.findByContentIgnoreCaseLike(search+"%"))
                postBySearch.add(post);
        }
        if (postService.findByAuthorIgnoreCaseLike(search+"%") != null) {
            for(Post post:postService.findByAuthorIgnoreCaseLike(search+"%"))
                postBySearch.add(post);
        }
        if(tagsService.findByNameIgnoreCaseLike(search+"%") != null){
            Tags theTag=tagsService.findByNameIgnoreCaseLike(search+"%").get(0);
            for(PostTags tag:postTagsService.findByTagId(theTag.getId()))
                postBySearch.add(postService.findById(tag.getPostId()));
        }
        theModel.addAttribute("posts", postBySearch);
        return "post";
    }*/

        //return filterValue;
//@ModelAttribute("comment") Comments theComment,
    }
