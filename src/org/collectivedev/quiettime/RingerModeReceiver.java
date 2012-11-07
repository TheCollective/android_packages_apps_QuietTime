/*-
 *  Copyright (C) 2010 Peter Baldwin   
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.collectivedev.quiettime;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.provider.Settings;

public class RingerModeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent data) {
        public boolean mQTEnabled;
        String action = data.getAction();
        if (AudioManager.RINGER_MODE_CHANGED_ACTION.equals(action)) {
            mQTEnabled = Settings.System.getBoolean(context.getContentResolver(), Settings.System.ENABLE_QUIETTIME, false);
    	if (mQTEnabled == true) {
            Timer timer = Timer.getInstance(context);
            int ringerMode = data.getIntExtra(AudioManager.EXTRA_RINGER_MODE, -1);
            if (ringerMode == AudioManager.RINGER_MODE_SILENT
                    || ringerMode == AudioManager.RINGER_MODE_VIBRATE) {
                if (!timer.isSet()) {
                    Object service = context.getSystemService(Context.KEYGUARD_SERVICE);
                    KeyguardManager manager = (KeyguardManager) service;
                    boolean unlocked = !manager.inKeyguardRestrictedInputMode();
                    boolean legacy = !TimerActivityLocked.isSupported();
                    Intent intent = new Intent(context, unlocked || legacy ? TimerActivity.class
                            : TimerActivityLocked.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            } else {
                timer.cancel();
            }
        }
    	} else {
		  return;
		}
    }
}
