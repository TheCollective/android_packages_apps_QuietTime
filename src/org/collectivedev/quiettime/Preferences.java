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

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.preference.PreferenceManager;

public class Preferences {
    public static final String VOLUME = "volume";

    public static SharedPreferences get(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static final int getVolume(Context context) {
        SharedPreferences preferences = get(context);
        AudioManager manager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int defaultValue = manager.getStreamMaxVolume(AudioManager.STREAM_RING);
        return preferences.getInt(VOLUME, defaultValue);
    }

    public static final void setVolume(Context context, int volume) {
        SharedPreferences preferences = get(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(VOLUME, volume);
        editor.commit();
    }
}
