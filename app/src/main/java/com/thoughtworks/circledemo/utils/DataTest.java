package com.thoughtworks.circledemo.utils;


import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.thoughtworks.circledemo.bean.CircleDynamicBean;
import com.thoughtworks.circledemo.bean.CommentsBean;
import com.thoughtworks.circledemo.bean.ImageBean;
import com.thoughtworks.circledemo.bean.PraiseBean;
import com.thoughtworks.circledemo.bean.SenderBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * =======================================================
 * project:CircleFriend
 * copyright：Copyright (C) 2017 SHANXI XIAN Technologies Ltd
 * author：xszhang
 * date：created by 2017/11/5 21:03
 * version：V1.0
 * descript：
 * =======================================================
 */
public class DataTest {
    public static ArrayList<CircleDynamicBean> circleList;
    private static int commentId = 0;
    /**
     * 动态id自增长
     */
    private static int circleId = 0;
    private static int userId = 104767419;
    /**
     * 点赞id自增长
     */
    private static int favortId = 0;
    public static final String[] HEADIMG = {
            "http://img.wzfzl.cn/uploads/allimg/140820/co140R00Q925-14.jpg",
            "http://www.feizl.com/upload2007/2014_06/1406272351394618.png",
            "http://v1.qzone.cc/avatar/201308/30/22/56/5220b2828a477072.jpg%21200x200.jpg",
            "http://v1.qzone.cc/avatar/201308/22/10/36/521579394f4bb419.jpg!200x200.jpg",
            "http://v1.qzone.cc/avatar/201408/20/17/23/53f468ff9c337550.jpg!200x200.jpg",
            "http://cdn.duitang.com/uploads/item/201408/13/20140813122725_8h8Yu.jpeg",
            "http://img.woyaogexing.com/touxiang/nv/20140212/9ac2117139f1ecd8%21200x200.jpg",
            "http://p1.qqyou.com/touxiang/uploadpic/2013-3/12/2013031212295986807.jpg"};

    public static final String[] CONTENTS = {"我们总是这样走，遇见了很多，也错过了很多。",
            "我晓得路的终点是天堂，却永远无法知道走路的过程。", "A man can be himself only so long as he is alone; and if he does not love solitude, he will not love freedom; for it is only when he is alone that he is really free. 一个人只有在独处时才能成为自己。谁要是不爱独处，那他就不爱自由，因为一个人只有在独处时才是真正自由的。",
            "翻着我们的照片，想念若隐若现，去年的冬天，我们笑得很甜......—— 哎哟，不错哦!", "青春是一本太仓促的书。我们不过就是在这一页页的纸张上填满沧桑罢了。 我们在扉页上写下序言 序言里爬满一条条的荒凉，然后我们写下尾语： 纪念我们失去的青春 最后找一副与青春无关的图画做了封面 草草了事了一段时光 然后留着给自己怀念那段无所事事的年华。 后来我也不知道，我是在怀念那段无所事事的荒唐年华，还是在怀念那段荒唐年华的无所事事。"};

    public static final String[] REPLYCONTENTS = {"我无法给你一个太阳但至少能给你一缕星光", "学着咽下一大段话和所有情绪，只用嗯表达所有想法。", "哈哈", "若是没有找到栖息的地方，到哪里都是流浪。我们都有一个，想回却回不了的过去。", "轻易不动感情的人，一旦动情，就会地裂山崩，把自己燃烧成一堆灰烬，被他爱上的人，也会被这狼烟烈火烧烤得痛不欲生。"};

    public static String[] mUrls = new String[]{
            "http://d.hiphotos.baidu.com/image/h%3D200/sign=201258cbcd80653864eaa313a7dca115/ca1349540923dd54e54f7aedd609b3de9c824873.jpg",
            "http://img3.fengniao.com/forum/attachpics/537/165/21472986.jpg",
            "http://d.hiphotos.baidu.com/image/h%3D200/sign=ea218b2c5566d01661199928a729d498/a08b87d6277f9e2fd4f215e91830e924b999f308.jpg",
            "http://img4.imgtn.bdimg.com/it/u=3445377427,2645691367&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=2644422079,4250545639&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=1444023808,3753293381&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=882039601,2636712663&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=4119861953,350096499&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=2437456944,1135705439&fm=21&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=3251359643,4211266111&fm=21&gp=0.jpg",
            "http://img4.duitang.com/uploads/item/201506/11/20150611000809_yFe5Z.jpeg",
            "http://img5.imgtn.bdimg.com/it/u=1717647885,4193212272&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=2024625579,507531332&fm=21&gp=0.jpg",
            "http://pic51.nipic.com/file/20141027/11284670_094822707000_2.jpg"};
    public static List<SenderBean> users = new ArrayList<SenderBean>();
    public static final SenderBean curUser = new SenderBean("10000", "自己", "小张", "http://www.feizl.com/upload2007/2014_06/1406272351394618.png");
    public static List<ImageBean> PHOTOS = new ArrayList<>();

    static {
        SenderBean user1 = new SenderBean("1", "吃鱼卡死的猫", "吃鱼卡死的猫", HEADIMG[1]);
        SenderBean user2 = new SenderBean("2", "因帅被判刑", "因帅被判刑", HEADIMG[2]);
        SenderBean user3 = new SenderBean("3", "隔壁老王", "隔壁老王", HEADIMG[3]);
        SenderBean user4 = new SenderBean("4", "爱像一阵风", "爱像一阵风", HEADIMG[4]);
        SenderBean user5 = new SenderBean("5", "杀死比尔", "杀死比尔", HEADIMG[5]);
        SenderBean user6 = new SenderBean("6", "Kobe", "Kobe", HEADIMG[6]);
        SenderBean user7 = new SenderBean("7", "这个名字是不是很长，哈哈！因为我是用来测试换行的", "test", HEADIMG[7]);

        users.add(curUser);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);

        ImageBean image = null;
        for (int i = 0; i < mUrls.length; i++) {
            String url = mUrls[i];
            image = new ImageBean();
            image.setUrl(url);
            PHOTOS.add(image);
        }
    }

    // 获取动态列表数据
    public static List<CircleDynamicBean> getCircleDyList() {
        List<CircleDynamicBean> list = new ArrayList<CircleDynamicBean>();
        list.clear();
        for (int i = 0; i < 15; i++) {
            CircleDynamicBean circleDyBean = new CircleDynamicBean();
            SenderBean albumUser = getUser();
            circleDyBean.setSender(albumUser);
            circleDyBean.setId(circleId++);
            circleDyBean.setContent(getContent());
            circleDyBean.setDt(String.valueOf(System.currentTimeMillis()));
            circleDyBean.setImages(createPhotos());
            circleDyBean.setComments(createCommentItemList());
            circleDyBean.setPraiseList(createPraiseItemList());
            list.add(circleDyBean);
        }
        return list;
    }


    public static List<CircleDynamicBean> getCircleDyData(String json) {
        if (json != null) {
            try {
                JSONArray array = new JSONArray(json);
                circleList = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    JSONObject jsonObject = array.getJSONObject(i);
                    CircleDynamicBean circle = JSON.parseObject(jsonObject.toString(), CircleDynamicBean.class);
                    //忽略不包含内容和图片的推文
                    if (TextUtils.isEmpty(circle.getContent()) && (circle.getImages() == null || circle.getImages().size() == 0)) {
                        continue;
                    }
                    circle.setId(circleId++);
                    circle.setPraiseList(createPraiseItemList());
                    circle.setDt(String.valueOf(System.currentTimeMillis()));
                    circleList.add(circle);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return circleList;
    }

    /**
     * 创建点赞列表
     *
     * @return
     */
    public static List<PraiseBean> createPraiseItemList() {
        int size = getRandomNum(users.size());
        List<PraiseBean> items = new ArrayList<PraiseBean>();
        List<String> history = new ArrayList<String>();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                PraiseBean newItem = createPraiseItem();
                String userId = newItem.getSenderBean().getId();
                if (!history.contains(userId)) {
                    items.add(newItem);
                    history.add(userId);
                } else {
                    i--;
                }
            }
        }
        return items;
    }


    /**
     * 创建点赞列表
     *
     * @return
     */
    public static PraiseBean createPraiseItem() {
        PraiseBean item = new PraiseBean();
        item.setId(String.valueOf(favortId++));
        item.setSenderBean(getUser());
        return item;
    }

    public static SenderBean getUser() {
        return users.get(getRandomNum(users.size()));
    }

    /**
     * 随机数
     *
     * @param max
     * @return
     */
    public static int getRandomNum(int max) {
        Random random = new Random();
        int result = random.nextInt(max);
        return result;
    }

    /**
     * 获取内容
     *
     * @return
     */
    public static String getContent() {
        return CONTENTS[getRandomNum(CONTENTS.length)];
    }

    /**
     * 创建图片
     *
     * @return
     */
    public static List<ImageBean> createPhotos() {
        List<ImageBean> photos = new ArrayList<ImageBean>();
        int size = getRandomNum(PHOTOS.size());
        if (size > 0) {
            if (size > 9) {
                size = 9;
            }
            for (int i = 0; i < size; i++) {
                ImageBean photo = PHOTOS.get(getRandomNum(PHOTOS.size()));
                if (!photos.contains(photo)) {
                    photos.add(photo);
                } else {
                    i--;
                }
            }
        }
        return photos;
    }

    /**
     * 创建回复列表item
     *
     * @return
     */
    public static List<CommentsBean> createCommentItemList() {
        List<CommentsBean> items = new ArrayList<CommentsBean>();
        int size = getRandomNum(10);
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                items.add(createComment());
            }
        }
        return items;
    }

    /**
     * 创建回复信息
     *
     * @return
     */
    public static CommentsBean createComment() {
        CommentsBean comment = new CommentsBean();
        comment.setCommentId(commentId++);
        comment.setContent(getReplyContent());
        SenderBean user = getUser();
        comment.setSender(user);
        if (getRandomNum(10) % 2 == 0) {
            while (true) {
                SenderBean replyUser = getUser();
                if (!user.getId().equals(replyUser.getId())) {
                    comment.setToReplyUser(replyUser);
                    break;
                }
            }
        }
        return comment;
    }

    /**
     * 获取评论内容
     *
     * @return
     */
    public static String getReplyContent() {
        return REPLYCONTENTS[getRandomNum(REPLYCONTENTS.length)];
    }

    /**
     * 创建当前点赞用户列表
     *
     * @return
     */
    public static PraiseBean createCurUserPraiseItem() {
        PraiseBean item = new PraiseBean();
        item.setId(String.valueOf(favortId++));
        item.setSenderBean(curUser);
        return item;
    }

    /**
     * 自己创建发布评论
     *
     * @return
     */
    public static CommentsBean createPublicComment(String content) {
        CommentsBean item = new CommentsBean();
        item.setCommentId(commentId++);
        item.setContent(content);
        item.setSender(curUser);
        return item;
    }

    /**
     * 创建回复评论
     *
     * @return
     */
    public static CommentsBean createReplyComment(SenderBean replyUser, String content) {
        CommentsBean item = new CommentsBean();
        item.setCommentId(commentId++);
        item.setContent(content);
        item.setSender(curUser);
        item.setToReplyUser(replyUser);
        return item;
    }
}
