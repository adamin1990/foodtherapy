package com.adam.food.presenter.main;

import com.adam.food.domain.TgClassifyWrapper;
import com.adam.food.model.main.ClassifyModel;
import com.adam.food.model.main.ClassifyModelImpl;
import com.adam.food.model.main.OnClassifyListener;
import com.adam.food.view.main.MainView;

import adamin.toolkits.utils.LogUtil;

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
 * //         Created by LiTao on 2016-03-09-16:48.
 * //         Company: QD24so
 * //         Email: 14846869@qq.com
 * //         WebSite: http://lixiaopeng.top
 * //
 */
public class ClassifyPresenter implements OnClassifyListener {
    private MainView mainView;
    private ClassifyModel classifyModel;

    public ClassifyPresenter(MainView mainView) {
        this.mainView = mainView;
        classifyModel=new ClassifyModelImpl();
    }
   public void getClassify(String name, int id){
       classifyModel.getClassify(name,id,this);
   }
    @Override
    public void before() {
        LogUtil.error(ClassifyModelImpl.class,"before");
        mainView.showLoading();

    }

    @Override
    public void onNext(TgClassifyWrapper tgClassifyWrapper,String name) {
        mainView.setData(tgClassifyWrapper,name);
        mainView.hideLoaidng();

    }

    @Override
    public void error(Throwable throwable) {
        mainView.showError(throwable);
        mainView.showErrorView();

    }

    @Override
    public void complete() {
        mainView.hideLoaidng();

    }
}
