package com.adam.food.model.main;

import com.adam.food.domain.TgClassifyWrapper;
import com.adam.food.service.ClassifyInterface;
import com.adam.food.utils.ServiceGenerator;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * //                           o8888888o
 * //                           88" . "88
 * //                           (| -_- |)
 * //                            O\ = /O
 * //                        ____/`---'\____
 * //                      .   ' \\| |// `.
 * //                       / \\||| : |||// \
 * //                     / _||||| -:- |||||- \
 * //                       | | \\\ - /// | |
 * //                     | \_| ''\---/'' | |
 * //                      \ .-\__ `-` ___/-. /
 * //                   ___`. .' /--.--\ `. . __
 * //                ."" '< `.___\_<|>_/___.' >'"".
 * //               | | : `- \`.;`\ _ /`;.`/ - ` : | |
 * //                 \ \ `-. \_ __\ /__ _/ .-` / /
 * //         ======`-.____`-.___\_____/___.-`____.-'======
 * //                            `=---='
 * //
 * //         .............................................
 * //                  佛祖镇楼                  BUG辟易
 * //          佛曰:
 * //                  写字楼里写字间，写字间里程序员；
 * //                  程序人员写程序，又拿程序换酒钱。
 * //                  酒醒只在网上坐，酒醉还来网下眠；
 * //                  酒醉酒醒日复日，网上网下年复年。
 * //                  但愿老死电脑间，不愿鞠躬老板前；
 * //                  奔驰宝马贵者趣，公交自行程序员。
 * //                  别人笑我忒疯癫，我笑自己命太贱；
 * //                  不见满街漂亮妹，哪个归得程序员？
 * //
 * //         Created by LiTao on 2016-03-09-16:20.
 * //         Company: QD24so
 * //         Email: 14846869@qq.com
 * //         WebSite: http://lixiaopeng.top
 * //
 */
public class ClassifyModelImpl implements ClassifyModel {
    @Override
    public void getClassify(String name, int id, final OnClassifyListener listener) {
        ServiceGenerator.createService(ClassifyInterface.class)
                .getClassify(name, id)
                .doOnNext(new Action1<TgClassifyWrapper>() {
                    @Override
                    public void call(TgClassifyWrapper tgClassifyWrapper) {
                        listener.before();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<TgClassifyWrapper>() {
                    @Override
                    public void call(TgClassifyWrapper tgClassifyWrapper) {
                        listener.onNext(tgClassifyWrapper);
                    }

                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        listener.error(throwable);
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        listener.complete();

                    }
                });
    }
}
