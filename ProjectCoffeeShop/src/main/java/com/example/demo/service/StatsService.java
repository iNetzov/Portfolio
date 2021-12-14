package com.example.demo.service;

import com.example.demo.models.view.StatsView;

public interface StatsService {
    void onRequest();
    StatsView getStats();
}
