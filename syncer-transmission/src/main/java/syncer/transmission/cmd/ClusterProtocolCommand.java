// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// See the License for the specific language governing permissions and
// limitations under the License.

package syncer.transmission.cmd;

/**
 * @author zhanenqiang
 * @Description 描述
 * @Date 2020/12/24
 */

import lombok.Builder;
import syncer.jedis.commands.ProtocolCommand;

/**
 * 命令模块
 */
@Builder
public class ClusterProtocolCommand implements ProtocolCommand {
    private byte[] raw;

    public ClusterProtocolCommand(byte[] raw) {
        this.raw = raw;
    }


    @Override
    public byte[] getRaw() {
        return this.raw;
    }

//    public static void main(String[] args) {
//        int a=1589777460000;
//    }
}