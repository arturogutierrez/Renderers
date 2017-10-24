package com.pedrogomez.renderers.sample.ui;

import android.content.Intent;
import android.os.Bundle;
import butterknife.OnClick;
import com.pedrogomez.renderers.sample.R;

/**
 * ListViewActivity for the Renderers demo.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class MainActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_main);
    super.onCreate(savedInstanceState);
  }

  @OnClick(R.id.bt_open_lv_sample) public void openListViewSample() {
    open(ListViewActivity.class);
  }

  @OnClick(R.id.bt_open_rv_sample) public void openRecyclerViewSample() {
    open(RecyclerViewActivity.class);
  }

  @OnClick(R.id.bt_open_multiselect_rv_sample) public void openMultiSelectRecyclerViewSample() {
    Intent intent = getIntentForClass(MultiSelectRecyclerViewActivity.class);
    intent.putExtra(MultiSelectRecyclerViewActivity.IS_MULTI_SELECT_EXTRA, true);
    open(intent);
  }

  @OnClick(R.id.bt_open_singleselect_rv_sample) public void openSingleSelectRecyclerViewSample() {
    open(MultiSelectRecyclerViewActivity.class);
  }

  private void open(Class activity) {
    startActivity(getIntentForClass(activity));
  }

  private void open(Intent intent) {
    startActivity(intent);
  }

  private Intent getIntentForClass(Class activity) {
    return new Intent(this, activity);
  }
}
