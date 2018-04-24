package test.zhmf.com;

import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by leo on 2018/4/24.
 */

public class TestAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {
    public TestAdapter(@Nullable List<Object> data) {
        super(R.layout.item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        TextView tv_test = helper.getView(R.id.tv_test);
        tv_test.setText(helper.getAdapterPosition() + "");
        LinearLayout ll_content = helper.getView(R.id.ll_content);
        ViewGroup.LayoutParams layoutParams = ll_content.getLayoutParams();
        layoutParams.height = 400;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        ll_content.setLayoutParams(layoutParams);
    }
}
