package com.listingmanager.services;

import com.google.api.services.mybusiness.v4.MyBusiness;
import com.google.api.services.mybusiness.v4.model.*;
import com.listingmanager.util.MyBusinessFactory;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import static com.listingmanager.util.MyBusinessFactory.getBusinessObject;

public class PostService {

    public enum POST_TYPE {
        LOCAL_POST_TOPIC_TYPE_UNSPECIFIED("LOCAL_POST_TOPIC_TYPE_UNSPECIFIED"), STANDARD("STANDARD"), EVENT("EVENT"), OFFER("OFFER");

        POST_TYPE(String value) {
            this.value = value;
        }

        private String value;

        public String getValue() {
            return this.value;
        }
    }

    public static void createPost(String locationName, LocalPost lp) throws GeneralSecurityException, IOException {
        if (locationName != null && locationName.trim() != "" && lp != null) {
            MyBusiness.Accounts.Locations.LocalPosts.Create create = MyBusinessFactory.getBusinessObject().accounts().locations().localPosts().create(locationName, lp);
            create.execute();
        }
    }

    public static void deletePost(String postName) throws GeneralSecurityException, IOException {
        if (postName != null && postName.trim() != "") {
            MyBusiness.Accounts.Locations.LocalPosts.Delete delete = getBusinessObject().accounts().locations().localPosts().delete(postName);
            delete.execute();

        }
    }

    public static void createPosts(String locationName, List<LocalPost> posts) throws GeneralSecurityException, IOException {
        if (posts != null && posts.size() > 0 && locationName != null && locationName.trim() != "") {
            for (LocalPost post : posts)
                createPost(locationName, post);
        }
    }

    public static void deletePosts(List<String> postNames) throws Exception {
        if (postNames != null && postNames.size() > 0)
            for (String postName : postNames)
                deletePost(postName);
    }

    public static ListLocalPostsResponse getPosts(String parent) throws GeneralSecurityException, IOException {
        MyBusiness.Accounts.Locations.LocalPosts posts = getBusinessObject().accounts().locations().localPosts();
        return posts.list(parent).execute();
    }

    /**
     * @param postType
     * @param summary
     * @param offer
     * @return
     */
    public static LocalPost buildLocalPost(POST_TYPE postType, String summary, LocalPostEvent event, List<MediaItem> mediaItems, LocalPostOffer offer)throws Exception {
        if (postType != null) {
            if(summary==null || summary.trim() == "")
                throw new Exception("Summary compulsory");

            LocalPost post = new LocalPost();
            post.setSummary(summary);
            post.setLanguageCode("en");
            post.setMedia(mediaItems);
            switch (postType) {
                case LOCAL_POST_TOPIC_TYPE_UNSPECIFIED:

                    break;
                case STANDARD:
                    post.setTopicType(POST_TYPE.STANDARD.getValue());
                    break;
                case EVENT:
                    post.setTopicType(POST_TYPE.EVENT.getValue());
                    post.setEvent(event);
                    break;
                case OFFER:
                    post.setTopicType(POST_TYPE.EVENT.getValue());
                    post.setEvent(event);
                    post.setOffer(offer);
                    break;
            }
            return post;
        }

        return null;
    }
}
