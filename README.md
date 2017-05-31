# MapWithSlidingUpPanelLayout
Test project

1 - Карта на основе Google Maps, метки на карте должны быть кликабельны, только по одной зоне (Москва). 
2 - Список снизу - максимальное количество записей 4, минимальное при свернутом состоянии -1 запись. Должны работать слайды вправо/влево для переключения страниц. Плашка сверху листа должна двигаться плавно.
3 - Иконки в топ баре справа / слева должны быть кликабельн. Поиск на карте также кликабельный.


## DEMO
---
-|-
-------------|----------------- 
![](https://github.com/dashika/MapWithSlidingUpPanelLayout/blob/master/Screenshot_2017-05-31-13-50-05.png)] |![](https://github.com/dashika/MapWithSlidingUpPanelLayout/blob/master/Screenshot_2017-05-31-13-50-10.png)]

#### Used libraries
---
    compile 'com.android.support:appcompat-v7:25.3.1'
    testCompile 'junit:junit:4.12'
    compile 'com.google.android.gms:play-services-maps:10.2.6'
    /* Bottom bar */
    compile 'com.sothree.slidinguppanel:library:3.3.1'
    /* ButterKnife */
    compile('com.jakewharton:butterknife:8.5.1') {
        exclude module: 'support-compat'
    }
    apt 'com.jakewharton:butterknife-compiler:8.5.1'
    compile 'com.android.support:design:25.3.1'
    compile 'com.android.support:support-v4:25.3.1'
    compile 'com.android.support:recyclerview-v7:25.3.1'
