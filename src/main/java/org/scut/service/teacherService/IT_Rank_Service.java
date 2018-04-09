package org.scut.service.teacherService;

import java.util.ArrayList;
import org.scut.model.*;
public interface IT_Rank_Service {
	public ArrayList<T_RankVO> getRankDetails(int homework_id);
}
