/*******************************************************************************
 * Copyright (c) 2016, 2017, Xavier Miret Andres <xavier.mires@gmail.com>
 *
 * Permission to use, copy, modify, and/or distribute this software for any 
 * purpose with or without fee is hereby granted, provided that the above 
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES 
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALLIMPLIED WARRANTIES OF 
 * MERCHANTABILITY  AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR 
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES 
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN 
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF 
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 *******************************************************************************/
package org.demo.model;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Encapsulate additional logic model related.
 */
@Service
public class UserTaskService
{
    @Autowired
    private UserTaskRepository utr;

    public Collection<UserTask> findAll()
    {
        return (Collection<UserTask>) utr.findAll();
    }

    public Collection<UserTask> findByUserName(String userName)
    {
        return utr.findByUserName(userName);
    }

    public synchronized UserTask save(UserTask userTask)
    {
        if (userTask.getId() != null && !assignable(userTask.getId(), userTask.getUserName())) throw new InvalidAssign();
        return utr.save(userTask);
    }

    private boolean assignable(Long id, String userName)
    {
        final UserTask ut = utr.findOne(id);
        return userName.equals(ut.getUserName()) || "-".equals(ut.getUserName());
    }

    public static class InvalidAssign extends RuntimeException
    {
        private static final long serialVersionUID = 1L;
    }
}
