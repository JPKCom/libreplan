/*
 * This file is part of NavalPlan
 *
 * Copyright (C) 2009 Fundación para o Fomento da Calidade Industrial e
 *                    Desenvolvemento Tecnolóxico de Galicia
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.navalplanner.web.planner.consolidations;

import static org.navalplanner.web.I18nHelper._;

import org.navalplanner.business.planner.entities.Task;
import org.navalplanner.business.planner.entities.TaskElement;
import org.navalplanner.web.planner.order.IEditTaskUtilities;
import org.navalplanner.web.planner.order.PlanningState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zkoss.ganttz.extensions.IContextWithPlannerTask;

/**
 * A command that opens a window to make the advances consolidations of a task.
 * @author Susana Montes Pedreira <smontes@wirelessgalicia.com>
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class AdvanceConsolidationCommand implements
        IAdvanceConsolidationCommand {

    private AdvanceConsolidationController advanceConsolidationController;
    private PlanningState planningState;

    @Autowired
    private IEditTaskUtilities editTaskUtilities;

    public AdvanceConsolidationCommand() {
    }

    @Override
    public void doAction(IContextWithPlannerTask<TaskElement> context,
            TaskElement task) {
        editTaskUtilities.reattach(task);

        if (isApplicableTo(task)) {
            this.advanceConsolidationController.showWindow(context,
                    (Task) task, planningState);
        }
    }

    @Override
    public String getName() {
        return _("Advance consolidation");
    }

    @Override
    public void initialize(
            AdvanceConsolidationController advanceConsolidationController,
            PlanningState planningState) {
        this.advanceConsolidationController = advanceConsolidationController;
        this.planningState = planningState;
    }

    @Override
    public String getIcon() {
        return "/common/img/ico_ok.png";
    }

    @Override
    public boolean isApplicableTo(TaskElement task) {
        return task instanceof Task;
    }

}
