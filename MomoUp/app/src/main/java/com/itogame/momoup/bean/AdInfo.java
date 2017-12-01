package com.itogame.momoup.bean;

import java.io.Serializable;


/**
 * @author loengcibo
 * @describe 启动页广告信息
 * @time 2017/11/29
 */
@SuppressWarnings("serial")
public class AdInfo implements Serializable {

    /**
     * id
     */
    private String id;

    /**
     * title﻿﻿﻿
     */
    private String title﻿﻿﻿;

    /**
     * subtitle
     */
    private String subtitle;

    /**
     * url
     */
    private String url;

    /**
     * icon﻿﻿
     */
    private String icon;

    /**
     * remaintTime
     */
    private String remaintTime;

    /**
     * 游戏id
     */
    private String game_id;

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle﻿﻿﻿() {
        return title﻿﻿﻿;
    }

    public void setTitle﻿﻿﻿(String title﻿﻿﻿) {
        this.title﻿﻿﻿ = title﻿﻿﻿;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon﻿﻿() {
        return icon;
    }

    public void setIcon﻿﻿(String icon) {
        this.icon = icon;
    }

    public String getRemaintTime() {
        return remaintTime;
    }

    public void setRemaintTime(String remaintTime) {
        this.remaintTime = remaintTime;
    }

}

