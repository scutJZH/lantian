package org.scut.service.impl.teacherImpl;

import java.util.ArrayList;
import org.scut.service.teacherService.*;
import org.scut.dao.teacherDao.*;
//import org.scut.model.*;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.scut.model.*;
@Service("t_Rank_Service")
public class T_Rank_ServiceImpl implements IT_Rank_Service{
	@Resource
	private IT_RankDAO t_Rank_Service;
	public ArrayList<T_RankVO> getRankDetails(int homework_id){
		return this.t_Rank_Service.getRankDetails(homework_id);
	}
}