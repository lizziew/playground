package elizabethwei.goldrush;

import android.os.Bundle;
import projectfun.mobile.ProjectFunMobileActivity;
import projectfun.mobile.ProjectFunMobileLogicTask;

public class GoldRushActivity extends ProjectFunMobileActivity {

	@Override
	public ProjectFunMobileLogicTask CreateActivityInterface(
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return new GoldRushTask();
	}

}
