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

import android.os.Build;

/**
 * An alias for {@link TimerActivity} that is shown with a different theme when
 * the phone is locked.
 */
public class TimerActivityLocked extends TimerActivity {
    @SuppressWarnings("deprecation")
    private static final int SDK = Integer.parseInt(Build.VERSION.SDK);

    private static final int ECLAIR = 5;

    public static boolean isSupported() {
        // The @android:style/Theme.Wallpaper.NoTitleBar theme used by 
        // this Activity is not availble on Cupcake and Donut.
        return SDK >= ECLAIR;
    }
}
