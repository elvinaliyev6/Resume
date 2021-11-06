/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.inter;

import com.company.entity.Skill;
import java.util.List;

/**
 *
 * @author Elvin
 */
public interface SkillDaoInter {

    public List<Skill> getAll();

    public boolean addSkill(Skill skill);

    public boolean removeSkill(int id);

    public Skill getById(int id);

    public boolean updateSkill(Skill skill);

    public List<Skill> getByName(String skillName);
}
