package com.listingmanager;


import com.google.api.services.mybusiness.v4.model.*;
import com.listingmanager.services.AccountService;
import com.listingmanager.services.LocationService;
import com.listingmanager.services.PostService;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args)throws Exception {
        //AccountService.getAccountList();
        //accounts/117692749187411798258 account name
        //System.out.println(LocationService.listLocations("accounts/117692749187411798258"));
        //accounts/117692749187411798258/locations/14413052432422241275 location name

        /*LocalPost post=new LocalPost();
        post.setLanguageCode("en");
        post.setSummary("Test Post 1");
        post.setTopicType("STANDARD");
        post.setState("LIVE");


        MediaItem mi = new MediaItem();
        mi.setSourceUrl("https://upload.wikimedia.org/wikipedia/commons/4/49/Modern_Script_A.svg");
        mi.setMediaFormat("PHOTO");*/

        //List<MediaItem>  mis = new ArrayList<MediaItem>();
        //mis.add(mi);
        //post.setMedia(mis);


        //LocalPostOffer offer = new LocalPostOffer();
        //offer.setCouponCode("CE345KF");
        //offer.setRedeemOnlineUrl("http://google.com");
        //offer.setTermsConditions("test offer");
        //post.setOffer(offer);
       //PostService.createPost("accounts/117692749187411798258/locations/14413052432422241275",post);

        //System.out.println(PostService.getPosts("accounts/117692749187411798258/locations/14413052432422241275"));
        //"accounts/-/locations/14413052432422241275/localPosts/615027060748428194"

        //PostService.deletePost("accounts/117692749187411798258/locations/14413052432422241275/localPosts/3980021726168296878");
        //PostService.createPost("accounts/117692749187411798258/locations/14413052432422241275",PostService.buildLocalPost(PostService.POST_TYPE.STANDARD,"Test 2 web service"));

        /*LocalPostEvent event = new LocalPostEvent();
        event.setTitle("Test Event Title");
        TimeInterval schedule = new TimeInterval();
        TimeOfDay st=new TimeOfDay();
        st.setNanos(0);st.setSeconds(0);st.setMinutes(0);st.setHours(14);
        schedule.setStartTime(st);
        TimeOfDay et = new TimeOfDay();
        et.setNanos(0);et.setSeconds(0);et.setMinutes(0);et.setHours(16);
        schedule.setEndTime(et);
        Date sd=new Date();
        sd.setYear(2018);
        sd.setMonth(8);
        sd.setDay(5);
        schedule.setStartDate(sd);
        Date ed=new Date();
        ed.setYear(2018);
        ed.setMonth(8);
        ed.setDay(5);
        schedule.setEndDate(ed);
        event.setSchedule(schedule);
*/

        //PostService.createPost("accounts/117692749187411798258/locations/14413052432422241275",PostService.buildLocalPost(PostService.POST_TYPE.EVENT,"Event Test1",event,mis,null));
        LocationService.listLocations("accounts/117692749187411798258");

    }
}
