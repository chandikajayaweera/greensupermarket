package com.greensupermarket.service;

import com.greensupermarket.model.Feedback;
import com.greensupermarket.dao.FeedbackDAO;

import java.util.List;

public class FeedbackService {
    
    private final Feedback feedback;
    private final FeedbackDAO feedbackDao;
    
    //Constructor
    public FeedbackService(){
        this.feedback = new Feedback();
        this.feedbackDao = new FeedbackDAO();
    }
    
    public boolean addFeedback(Feedback feedback){
        return feedbackDao.addFeedback(feedback);
    }
    
    public List<Feedback> getAllFeedbacks(){
        return feedbackDao.getAllFeedback();
    }
}
