package com.example.utils;

import android.util.Log;

import com.example.model.ChargeOrder;
import com.example.model.ChatMsg;
import com.example.model.Discuss;
import com.example.model.Moneny;
import com.example.model.Related;
import com.example.model.AdviseBean;
import com.example.model.Attention;
import com.example.model.ButtonType;
import com.example.model.Classify;
import com.example.model.Comments;
import com.example.model.Famous;
import com.example.model.Friend;
import com.example.model.Information;
import com.example.model.Interlocution;
import com.example.model.Live;
import com.example.model.Message;
import com.example.model.OldLive;
import com.example.model.Opinion;
import com.example.model.Person;
import com.example.model.Press;
import com.example.model.Province;
import com.example.model.School;
import com.example.model.TextVideo;
import com.example.model.Video;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jucaipen on 16/5/30.
 */
public class JsonUtil {
    /*
       解析广告信息
     */
    public static List<AdviseBean> getAdvise(String result) {
        List<AdviseBean> advises = new ArrayList<>();
        AdviseBean advise = new AdviseBean();
        try {
            JSONArray array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int id = object.getInt("id");
                String title = object.getString("title");
                int typeId = object.getInt("typeId");
                String linkUrl = object.getString("linkUrl");
                int sortId = object.getInt("sortId");
                advise.setId(id);
                advise.setTitle(title);
                advise.setLinkUrl(linkUrl);
                advise.setSortId(sortId);
                advises.add(advise);
            }
            return advises;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析推荐名师界面
     */
    public static List<Famous> getFamous(String famous) {
        List<Famous> famouses = new ArrayList<>();
        try {
            JSONArray array1 = new JSONArray(famous);
            for (int i = 0; i < array1.length(); i++) {
                JSONObject object = array1.getJSONObject(i);
                int page = object.getInt("page");
                int totpage = object.getInt("totlePage");
                int id = object.getInt("id");
                String nickName = object.getString("nickName");
                String headFace = object.getString("headFace");
                String lev = object.getString("level");
                int isv = object.getInt("isV");
                String notice = object.getString("notice");
                int fan = object.getInt("fans");
                Famous famoues = new Famous();
                famoues.setPage(page);
                famoues.setTotpager(totpage);
                famoues.setId(id);
                famoues.setNinkName(nickName);
                famoues.setHeadFace(headFace);
                famoues.setLevel(lev);
                famoues.setIsv(isv);
                famoues.setFans(fan);
                famoues.setNotice(notice);
                famouses.add(famoues);
            }
            return famouses;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析获取观点
     */
    public static List<Opinion> getOpinion(String opinion, List<Opinion> opinionList) {
        try {

            //[{"page":1,"totlePage":1,"askId":173,"trueName":null,"insertDate":"2016-05-23 10:02:00.71",
            // "askBodys":"1111","headFace":null,"isPay":1,"replyCount":0,"answerBody":null}

            //[{"page":0,"totlePage":0,"id":796,"insertDate":null,"title":"加入国际指数A股能获得什么？",
            // "hits":0,"goods":0,"teacherId":0,"nickName":null,"level":null,"headFace":null,"isV":0}


            JSONArray array = new JSONArray(opinion);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int page = object.optInt("page", -1);
                int totlepage = object.optInt("totlePage", -1);
                int id = object.optInt("id", -1);
                String insert = object.optString("insertDate", "");
                String title = object.optString("title", "");
                String content = object.optString("body", "");
                int click = object.optInt("hits", -1);
                int like = object.optInt("goods", -1);
                int teacherid = object.optInt("teacherId", -1);
                String name = object.optString("nickName", "");
                String lev = object.optString("level", "");
                String portrait = object.optString("headFace", "");
                int isv = object.optInt("isV", -1);


                Opinion opinion1 = new Opinion();
                opinion1.setPage(page);
                opinion1.setTotpager(totlepage);
                opinion1.setId(id);
                opinion1.setInsertdate(insert);
                opinion1.setTitle(title);
                opinion1.setBodys(content);
                opinion1.setHits(click);
                opinion1.setGoods(like);
                opinion1.setTeacherId(teacherid);
                opinion1.setNickname(name);
                opinion1.setLevel(lev);
                opinion1.setHeadface(portrait);
                opinion1.setIsv(isv);
                opinionList.add(opinion1);
            }
            return opinionList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析获取问答
     */
    public static List<Interlocution> getInter(String interlocution) {

        List<Interlocution> list = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(interlocution);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int page = object.optInt("page", -1);
                int totlePage = object.optInt("totlePage", -1);
                int askId = object.optInt("askId", -1);
                String trueName = object.optString("trueName", "");
                String insertDate = object.optString("insertDate", "");
                String askBodys = object.optString("askBodys", "");
                String headFace = object.optString("headFace", "");
                int isReply = object.optInt("replyCount", -1);
                int isPay = object.optInt("isPay", -1);
                String answerBody = object.optString("answerBody", "");

                Interlocution interlocution1 = new Interlocution();
                interlocution1.setPage(page);
                interlocution1.setTotlePage(totlePage);
                interlocution1.setAskId(askId);
                interlocution1.setTrueName(trueName);
                interlocution1.setInsertDate(insertDate);
                interlocution1.setAskBodys(askBodys);
                interlocution1.setHeadFace(headFace);
                interlocution1.setIsReply(isReply);
                interlocution1.setIsPay(isPay);
                interlocution1.setAnswerBody(answerBody);
                list.add(interlocution1);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析问题分类
     */
    public static List<Classify> getClassify(String Classify) {
        List<Classify> classifies = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(Classify);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int id = object.getInt("classId");
                String name = object.getString("typeName");
                Classify classify = new Classify();
                classify.setId(id);
                classify.setName(name);
                classifies.add(classify);
            }
            return classifies;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析今日热点、新闻
     */
    public static List<Press> getpress(String press) {
        try {
            List<Press> list = new ArrayList<>();
            JSONArray array = new JSONArray(press);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);

//                int pager = object.getInt("pager");
//                int totlePager = object.getInt("totlePager");
                int id = object.getInt("id");
                String title = object.getString("title");
                int comms = object.getInt("comms");
                String imageThumb = object.getString("imageUrl");
                String insertDate = object.getString("InsertDate");
                String from = object.getString("from");

                Press press1 = new Press();
//                press1.setPager(pager);
//                press1.setTotpager(totlePager);
                press1.setId(id);
                press1.setTitle(title);
                press1.setComms(comms);
                press1.setImageThumb(imageThumb);
                press1.setInsertDate(insertDate);
                press1.setFrom(from);

                list.add(press1);
            }

            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取相关新闻
     */
    public static List<Related> getRelated(String Realated) {
        List<Related> relateds = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(Realated);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int id = object.getInt("id");
                String title = object.getString("title");
                String from = object.getString("from");
                int comms = object.getInt("comms");
                String insertDate = object.getString("insertDate");
                String image = object.getString("image");
                Related related = new Related();
                related.setId(id);
                related.setTitle(title);
                related.setFrom(from);
                related.setComms(comms);
                related.setInsertDate(insertDate);
                related.setImage(image);
                relateds.add(related);
            }
            return relateds;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取评论信息
     */
    public static List<Discuss> getDiscuss(String dis) {
        List<Discuss> discusses = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(dis);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int id = object.getInt("id");
                String headFace = object.getString("headFace");
                String nickName = object.getString("nickName");
                String insertDate = object.getString("insertDate");
                String bodys = object.getString("bodys");
                int respCount = object.getInt("respCount");
                Discuss discuss = new Discuss();
                discuss.setId(id);
                discuss.setHeadFace(headFace);
                discuss.setNickName(nickName);
                discuss.setInsertDate(insertDate);
                discuss.setBodys(bodys);
                discuss.setRespCount(respCount);
                discusses.add(discuss);
            }
            return discusses;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 完善个人信息
     */
    public static Message getMessage(String message) {
        try {
            JSONObject object = new JSONObject(message);
            int id = object.getInt("ret_code");
            String msg = object.getString("err_msg");
            Message message1 = new Message();
            message1.setId(id);
            message1.setMsg(msg);
            return message1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取我的信息
     */
    public static Information getinter(String infor) {
        try {
            JSONObject object = new JSONObject(infor);
            String nick = object.getString("nickName");
            String number = object.getString("account");
            String sex = object.getString("sex");
            String name = object.getString("trueName");
            String birthday = object.getString("birthday");
            String localCity = object.getString("localCity");
            String investType = object.getString("investType");
            String descript = object.getString("descript");
            String faceImage = object.getString("faceImage");
            Information information = new Information();
            information.setNickName(nick);
            information.setAccount(number);
            information.setSex(sex);
            information.setTrueName(name);
            information.setBirthday(birthday);
            information.setLocalCity(localCity);
            information.setInvestType(investType);
            information.setDescript(descript);
            information.setFaceImage(faceImage);
            return information;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取省份信息
     */
    public static List<Province> getprovince(String province) {
        List<Province> provinces = new ArrayList<>();
        Province province1 = new Province();
        try {
            JSONArray array = new JSONArray(province);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = new JSONObject(province);
                int pager = object.getInt("pager");
                int totlePager = object.getInt("totlePager");
                int id = object.getInt("id");
                String name = object.getString("name");
                int ProSort = object.getInt("ProSort");
                province1.setPager(pager);
                province1.setTotlePager(totlePager);
                province1.setId(id);
                province1.setName(name);
                province1.setProSort(ProSort);
                provinces.add(province1);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    * 首页 视频信息
    * */
    public static List<Video> getVide(String result) {
        List<Video> list = new ArrayList<>();
        JSONArray array = null;
        try {
            array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                Video video = new Video();
                int id = object.getInt("id");
                int classId = object.getInt("classId");
                String title = object.getString("title");
                String imageUrl = object.getString("imageUrl");
                String videoUrl = object.optString("videoUrl", "");
                String dec = object.getString("desc");
                boolean isSpecial = object.getBoolean("isSpecial");
                boolean isCharge = object.getBoolean("isCharge");
                video.setId(id);
                video.setCharge(isCharge);
                video.setSpecial(isSpecial);
                video.setClassId(classId);
                video.setTitle(title);
                video.setImageUrl(imageUrl);
                video.setVideoUrl(videoUrl);
                video.setDesc(dec);
                list.add(video);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return list;
    }


    /**
     * 解析今日热点详情
     */
    public static Press gethotsee(String str) {
        try {
            JSONObject object = new JSONObject(str);

            //  {"title":"标题","insertDate":"发布时间","teacherFace":"讲师头像","teacherName":"讲师名称",
            // "imageUrl":观点封面图片,"body":"内容","readNum":阅读数,"goods":点赞数}


            String title = object.getString("title");
            String insertDate = object.getString("insertDate");

            String teacherFace = object.getString("teacherFace");
            String teacherName = object.getString("teacherName");

            String imagUrl = object.getString("imageUrl");
            String body = object.getString("body");
            int readNum = object.getInt("readNum");
            int goods = object.optInt("goods");


//            String from = object.getString("from");
//            String keyWord = object.getString("keyWord");
            // String shareUrl = object.getString("shareUrl");


            Press press = new Press();
            press.setTitle(title);
            //press.setFrom(from);
            press.setTeacherFace(teacherFace);
            press.setTeacherName(teacherName);
            press.setInsertDate(insertDate);
            press.setImagUrl(imagUrl);
            press.setBody(body);
            press.setReadNum(readNum);
            press.setGoods(goods);
            // press.setKeyWord(keyWord);
            // press.setShareUrl(shareUrl);

            return press;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析直播的     文字直播  视频直播
     */
    public static List<TextVideo> getTextVideo(String str) {
        List<TextVideo> list = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(str);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int id = object.optInt("id", -1);
                String title = object.optString("title", "");
                String images = object.optString("images", "");
                String teacherName = object.optString("teacherName", "");
                String teacherFace = object.optString("teacherFace", "");
                int isV = object.optInt("isV", 0);
                int attentNum = object.optInt("attentNum", -1);
                int teacherId = object.optInt("teacherId", -1);

                TextVideo video = new TextVideo();
                video.setId(id);
                video.setTitle(title);
                video.setImages(images);
                video.setTeacherName(teacherName);
                video.setTeacherFace(teacherFace);
                video.setIsV(isV);
                video.setTeacherId(teacherId);
                video.setAttentNum(attentNum);
                list.add(video);
            }
            return list;

        } catch (JSONException e) {
        }
        return null;
    }

    /*
    * 学院视频解析
    * */

    public static List<School> getschool(String str) {
        List<School> list = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(str);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.optJSONObject(i);
                int id = object.optInt("id", -1);
                String title = object.optString("title", "");
                String desc = object.optString("desc", "");
                String insertDate = object.optString("insertDate", "");
                int hits = object.optInt("hits", -1);
                String image = object.optString("image", null);
                String videoUrl = object.optString("videoUrl", "");
                School school = new School();
                school.setId(id);
                school.setTitle(title);
                school.setDesc(desc);
                school.setInsertDate(insertDate);
                school.setVideoUrl(videoUrl);
                school.setHits(hits);
                school.setImage(image);
                list.add(school);
            }
            return list;


        } catch (JSONException e) {

        }

        return null;
    }

    /*
    * 解析学院按钮
    * */
    public static List<ButtonType> getbutton(String str) {
        List<ButtonType> list = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(str);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.optJSONObject(i);
                int id = object.optInt("id", -1);
                String bigType = object.optString("bigType", "");
                ButtonType buttonType = new ButtonType();
                buttonType.setId(id);
                buttonType.setBigType(bigType);
                list.add(buttonType);
            }
            return list;
        } catch (JSONException e) {
        }
        return null;
    }


    /*
    首页视频 解析
    * */
    public static List<Live> getLiveType(String str) {
        List<Live> list = new ArrayList<>();
        JSONArray array = null;
        try {
            array = new JSONArray(str);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.optJSONObject(i);
                int id = object.getInt("id");
                String title = object.optString("title", "");
                String nickName = object.optString("nickName", "");
                Live live = new Live();
                live.setId(id);
                if (title != null && title.length() > 0) {
                    live.setTitle(title);
                } else {
                    live.setTitle(nickName);
                }
                list.add(live);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    /*
  * 解析首页视频  选集
  * */
    public static List<Video> getvideo(String str) {
        List<Video> list = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(str);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.optJSONObject(i);
                int id = object.optInt("id", -1);
                int page = object.optInt("page", -1);
                int totlePage = object.optInt("totlePage", -1);
                String title = object.optString("title", "");
                String imageUrl = object.optString("imageUrl", "");
                String desc = object.optString("desc", "");
                String videUrl = object.optString("videoUrl", "");
                Video video = new Video();
                video.setId(id);
                video.setPage(page);
                video.setTotlePage(totlePage);
                video.setTitle(title);
                video.setImageUrl(imageUrl);
                video.setDesc(desc);
                video.setVideoUrl(videUrl);
                list.add(video);
            }
            return list;
        } catch (JSONException e) {
        }
        return null;
    }


    /*
    * 解析点播评论
    * */

    // [{"id":79,"insertDate":"2016-06-14 16:40:55.0","reptCount":0,"body":"OK","userName":"datou",
    // "headFace":"http://img.jucaipen.com/jucaipenUpload/2016/3/30/201633016549.jpg","userLeavel":0},




   /* [   { "id": 28,
            "headFace":用户头像,
            "nickName": 用户昵称,
            "insertDate": "评论时间",
            "bodys": "评论内容",
            "respCount": 回复数,
            "goods": 点赞数,
            "userLeavel": 用户等级
    }]*/


    public static List<Comments> getcomments(String str) {
        List<Comments> list = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(str);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.optJSONObject(i);

                int id = object.optInt("id", -1);
                String insertDate = object.optString("insertDate", "");
                int reptCount = object.optInt("respCount", -1);
                String body = object.optString("bodys", "");
                String userName = object.optString("nickName", "");
                String headFace = object.optString("headFace", "");
                int userLeavel = object.optInt("userLeavel", -1);
                int goods = object.optInt("goods", -1);
                Comments comments = new Comments();
                comments.setId(id);
                comments.setInsertDate(insertDate);
                comments.setReptCount(reptCount);
                comments.setBody(body);
                comments.setUserName(userName);
                comments.setHeadFace(headFace);
                comments.setUserLeavel(userLeavel);
                comments.setGoods(goods);
                list.add(comments);
            }
            return list;
        } catch (JSONException e) {
        }
        return null;
    }

    /*
    * 解析推荐视频
    * */

    public static List<Video> getaboutvideo(String str) {

        List<Video> list = new ArrayList<>();
        //{"id":427,"title":"投资互动《第二期5.19》","imageUrl":"http37.jpg","videoDate":0,"hits":17,"isSpecial":false}
        try {
            JSONArray array = new JSONArray(str);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int page = object.optInt("page", -1);
                int totlePage = object.optInt("totlePage", -1);
                int id = object.optInt("id", -1);
                int classId = object.optInt("classId", -1);
                String title = object.optString("title", "");
                String videoUrl = object.optString("videoUrl", "");
                String imageUrl = object.optString("imageUrl", "");
                String desc = object.optString("desc", "");
                int playCount = object.optInt("playCount", -1);
                String videoDate = object.optString("videoDate", null);
                Video video = new Video();
                video.setPage(page);
                video.setTotlePage(totlePage);
                video.setId(id);
                video.setClassId(classId);
                video.setTitle(title);
                video.setVideoUrl(videoUrl);
                video.setDesc(desc);
                video.setPlayCount(playCount);
                video.setVideoDate(videoDate);
                video.setImageUrl(imageUrl);
                list.add(video);
            }
            return list;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


    /*
    * 解析个人资料
    * */
    public static Person getMyInfo(String str) {
        try {
            JSONObject object = new JSONObject(str);
            String nickName = object.optString("nickName", "");
            String sex = object.optString("sex", "");
            String account = object.optString("account", "");
            String trueName = object.optString("trueName", "");
            String birthday = object.optString("birthday", "");
            String localCity = object.optString("localCity", "");
            String investType = object.optString("investType", "");
            String descript = object.optString("descript", "");
            String faceImage = object.optString("faceImage", "");
            Person person = new Person();
            person.setNickName(nickName);
            person.setSex(sex);
            person.setAccount(account);
            person.setTrueName(trueName);
            person.setBirthday(birthday);
            person.setLocalCity(localCity);
            person.setInvestType(investType);
            person.setInvestType(investType);
            person.setDescript(descript);
            person.setFaceImage(faceImage);
            return person;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    /*
    * 解析 积分  聚财币 数据
    * */

    public static List<Moneny> getBills(String str) {

        List<Moneny> list = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(str);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int page = object.optInt("page", -1);
                int totlePage = object.optInt("totlePage", -1);
                int id = object.optInt("id", -1);
                String insertDate = object.optString("insertDate", "");
                double detailMoney = object.optDouble("detailMoney", 0.00);
                int type = object.optInt("type", -1);
                String remark = object.optString("remark", "");
                int Integeral = object.optInt("Integeral", -1);
                int jucaiBills = object.optInt("jucaiBills", -1);


                Moneny moneny = new Moneny();
                moneny.setPage(page);
                moneny.setTotlePage(totlePage);
                moneny.setId(id);
                moneny.setInsertDate(insertDate);
                moneny.setDetailMoney(detailMoney);
                moneny.setTotlePage(type);
                moneny.setRemark(remark);
                moneny.setIntegeral(Integeral);
                moneny.setJucaiBills(jucaiBills);
                list.add(moneny);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


    /*
    * 解析我的关注
    * */
    public static List<Attention> getAttention(String str) {

        List<Attention> list = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(str);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int page = object.optInt("page", -1);
                int totlePage = object.optInt("totlePage", -1);
                int id = object.optInt("id", -1);
                String teacherImage = object.optString("teacherImage", "");
                String nickName = object.optString("nickName", "");
                int isV = object.optInt("isV", -1);
                String notice = object.optString("notice", "");
                int fans = object.optInt("fans", -1);
                String leavel = object.optString("leavel", "");

                Attention attention = new Attention();
                attention.setPage(page);
                attention.setTotlePage(totlePage);
                attention.setId(id);
                attention.setTeacherImage(teacherImage);
                attention.setNickName(nickName);
                attention.setIsV(isV);
                attention.setNotice(notice);
                attention.setFans(fans);
                attention.setLeavel(leavel);
                list.add(attention);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    /*
    * 解析我的守护
    * */

    public static List<Attention> getGuard(String str) {

        List<Attention> list = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(str);
            for (int i = 0; i < array.length(); i++) {

                //[{"page":1,"totlePage":1,"id":25,"startDate":"2016-06-16 17:59:07.07",
                // "endDate":"2016-06-17 17:59:07.07","teacherImage":"http://img.j47.jpg",
                // "nickName":"远清看市","leavel":"CCTV产品讲师","isV":1,
                // "introduce":"<spne-h\">现任CCTV在线教学课堂《证券实战教学》栏目公开课讲师。</span>"}]

                JSONObject object = array.getJSONObject(i);
                int page = object.optInt("page", -1);
                int totlePage = object.optInt("totlePage", -1);
                int id = object.optInt("id", -1);
                String startDate = object.optString("startDate", "");
                String endDate = object.optString("endDate", "");
                String teacherImage = object.optString("teacherImage", "");
                String nickName = object.optString("nickName", "");
                String leavel = object.optString("leavel", "");
                int isV = object.optInt("isV", -1);
                String introduce = object.optString("introduce", "");
                Attention attention = new Attention();
                attention.setPage(page);
                attention.setTotlePage(totlePage);
                attention.setId(id);
                attention.setStartDate(startDate);
                attention.setEndDate(endDate);
                attention.setTeacherImage(teacherImage);
                attention.setNickName(nickName);
                attention.setIsV(isV);
                attention.setLeavel(leavel);
                attention.setIntroduce(introduce);
                list.add(attention);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    /*
    * 解析我的邀请
    * */
    public static List<Friend> getinvite(String str) {
        List<Friend> list = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(str);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int page = object.optInt("page", -1);
                int totlePage = object.optInt("totlePage", -1);
                int id = object.optInt("id", -1);
                String recommderPhone = object.optString("recommderPhone", "");

                String recommderDate = object.optString("recommderDate", "");
                String recommder = object.optString("recommder", "");
                String recommderImage = object.optString("recommderImage", "");
                Friend friend = new Friend();
                friend.setPage(page);
                friend.setTotlePage(totlePage);
                friend.setId(id);
                friend.setRecommderDate(recommderDate);
                friend.setRecommder(recommder);
                friend.setRecommderPhone(recommderPhone);
                friend.setRecommderImage(recommderImage);
                list.add(friend);
            }
            return list;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    /*
    * 解析支付数据
    * */

    public static List<ChargeOrder> getmyorder(String result) {
        //[{"id":0,"insertDate":"2016-06-17 13:26:56.79","orderCode":"616061713265679131","chargeMoney":10.0,"payType":2,"state":1}]

        List<ChargeOrder> orders = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int page = object.optInt("page", 0);
                int totlePage = object.optInt("totlePage", 0);
                int id = object.getInt("id");
                String insertDate = object.getString("insertDate");
                String orderCode = object.getString("orderCode");
                double chargeMoney = object.getDouble("chargeMoney");
                int payType = object.getInt("payType");
                int payState = object.getInt("state");

                ChargeOrder order = new ChargeOrder();
                order.setPage(page);
                order.setTotlePage(totlePage);
                order.setChargeMoney(chargeMoney);
                order.setOrderState(payState);
                order.setId(id);
                order.setPayType(payType);
                order.setOrderCode(orderCode);
                order.setInsertDate(insertDate);
                orders.add(order);


            }
            return orders;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    * 解析演播室 往期视频
    * */

    public static List<OldLive> getOldLive(String result) {
        List<OldLive> list = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int id = object.optInt("id", -1);
                String title = object.optString("title", "");
                String imageUrl = object.optString("imageUrl", "");
                int videoDate = object.optInt("videoDate", -1);
                String videoUrl = object.optString("videoUrl", "");
                int hits = object.optInt("hits", 0);
                boolean isSpecial = object.optBoolean("isSpecial", false);

                OldLive live = new OldLive();
                live.setId(id);
                live.setVideoUrl(videoUrl);
                live.setTitle(title);
                live.setImageUrl(imageUrl);
                live.setVideoDate(videoDate);
                live.setHits(hits);
                live.setSpecial(isSpecial);
                list.add(live);

            }
            return list;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    /*
    * 解析聊天数据
    *
    * */

    public static List<ChatMsg> getMsg(String result, List<ChatMsg> chatList) {
        Log.i("111", "getMsg: "+result);
        try {
            JSONArray array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++) {
                //[{"sendId":48,"sendName":"学习找牛股","msg":"份饭","shenHe":0,"sendLeavel":0,"toName":null}]
                JSONObject object = array.getJSONObject(i);
                int sendId = object.optInt("sendId", -1);
                String sendName = object.optString("sendName", "");
                String msg = object.optString("msg", "");
                int shenHe = object.optInt("shenHe", -1);
                int sendLeavel = object.optInt("sendLeavel", -1);
                String toName = object.optString("toName", "");
                String fromFace=object.getString("fromFace");
                String toFace=object.getString("toFace");
                String sendDate=object.getString("sendDate");
                ChatMsg chatMsg = new ChatMsg();
                chatMsg.setSendId(sendId);
                chatMsg.setSendName(sendName);
                chatMsg.setMsg(msg);
                chatMsg.setToFace(toFace);
                chatMsg.setSendDate(sendDate);
                chatMsg.setFromFace(fromFace);
                chatMsg.setShenHe(shenHe);
                chatMsg.setSendLeavel(sendLeavel);
                chatMsg.setToName(toName);
                chatList.add(chatMsg);
            }
            return chatList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


}