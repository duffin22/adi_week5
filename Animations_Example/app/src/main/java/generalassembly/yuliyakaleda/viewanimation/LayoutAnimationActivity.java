package generalassembly.yuliyakaleda.viewanimation;


import android.animation.LayoutTransition;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class LayoutAnimationActivity extends AppCompatActivity {
  private ViewGroup viewGroup;
  private Button add, delete;
  private int count;


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_animation_activity);
    LayoutTransition l = new LayoutTransition();
    l.enableTransitionType(LayoutTransition.CHANGING);
    viewGroup = (ViewGroup) findViewById(R.id.ll);
    viewGroup.setLayoutTransition(l);

    count = 0;

    add = (Button) findViewById(R.id.add);
    add.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        viewGroup.addView(new Button(LayoutAnimationActivity.this));
        count++;
      }
    });

    delete = (Button) findViewById(R.id.delete);
    delete.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (count > 0) {
          viewGroup.removeViewAt(0);
          count--;
        } else {
          Toast.makeText(LayoutAnimationActivity.this, "Unable to delete a button at this time", Toast.LENGTH_SHORT).show();
        }
      }
    });


  }
}
