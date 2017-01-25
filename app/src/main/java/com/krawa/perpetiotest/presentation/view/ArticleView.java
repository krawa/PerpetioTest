package com.krawa.perpetiotest.presentation.view;

import com.krawa.perpetiotest.model.News;

public interface ArticleView extends BaseFragmentView {
    void bindArticle(News news);
}
