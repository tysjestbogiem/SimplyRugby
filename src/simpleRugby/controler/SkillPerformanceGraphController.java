package simpleRugby.controler;

import java.util.List;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import simpleRugby.model.PlayerPerformanceDAO;
import simpleRugby.model.Skill;
import simpleRugby.model.SkillPerformanceGraphDAO;
import simpleRugby.view.SkillPerformanceGraph;


public class SkillPerformanceGraphController {
	
	private SkillPerformanceGraphDAO mySkillPerformanceGraphDAO;
    private SkillPerformanceGraph mySkillPerformanceGraph;

    public SkillPerformanceGraphController(SkillPerformanceGraphDAO mySkillPerformanceGraphDAO, SkillPerformanceGraph mySkillPerformanceGraph) {
        this.mySkillPerformanceGraphDAO = mySkillPerformanceGraphDAO;
        this.mySkillPerformanceGraph = mySkillPerformanceGraph;
        
    }


    
    public CategoryDataset createDataset(List<Skill> skills) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for (Skill skill : skills) {
            String skillName = skill.getSkillName();
            String trainingDate = skill.getTrainingDateChanged();
            int level = (int) Math.round(skill.getLevel());  // to get integer
            
            if (skillName != null && trainingDate != null) { // if null valuse skip
                dataset.addValue(level, skillName, trainingDate);
            }
        }

        return dataset;
    }
}
       
     