package ch.ethz.inf.vs.talosmodule.communication.messages;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ch.ethz.inf.vs.talosmodule.main.TalosCommand;

/*
 * Copyright (c) 2016, Institute for Pervasive Computing, ETH Zurich.
 * All rights reserved.
 *
 * Author:
 *       Lukas Burkhalter <lubu@student.ethz.ch>
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * ``AS IS'' AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE
 * COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */

public class CommandRequestMessage extends RequestMessage {

    protected TalosCommand command;

    public CommandRequestMessage(String idtoken, TalosCommand command) {
        super(idtoken);
        this.command = command;
    }

    @Override
    public String toJSON() {
        JSONObject commandJo = new JSONObject();
        JSONObject res = new JSONObject();
        JSONArray args= new JSONArray();
        try {
            res.put(MessagesUtils.JSON_ID_TOKEN_KEY, this.getIdtoken());
            commandJo.put(MessagesUtils.JSON_COMMAND_KEY, command.getCommandName());
            for(int ind=0; ind<command.numCiphers(); ind++) {
                args.put(command.getCipherAtIndex(ind).getStringRep());
            }
            commandJo.put(MessagesUtils.JSON_ARGS_KEY, args);
            res.put(MessagesUtils.JSON_COMMADN_HEAD, commandJo);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return res.toString();
    }
}
