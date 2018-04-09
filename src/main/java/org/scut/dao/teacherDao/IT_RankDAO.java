package org.scut.dao.teacherDao;

import java.util.ArrayList;
import org.scut.model.*;

public interface IT_RankDAO {
	public ArrayList<T_RankVO> getRankDetails(int homework_id);
}
