"""
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

"""

from resource_management import *
import sys

class RabbitMQServer(Script):

  def install(self, env):
    self.install_packages(env)
    import params
    env.set_params(params)

  def configure(self, env):
    import params
    env.set_params(params)

  def start(self, env):
    import params
    env.set_params(params)
    self.configure(env)
    daemon_cmd = format('{rabbitmq_server_bin} start')
    no_op_test = format('ls {rabbitmq_server_pid_file} >/dev/null 2>&1 && ps `cat {rabbitmq_server_pid_file}` >/dev/null 2>&1')
    Execute(daemon_cmd,
            environment={'HOME': params.rabbitmq_server_home},
            not_if=no_op_test
    )

  def stop(self, env):
    import params
    env.set_params(params)
    self.configure(env)
    daemon_cmd = format('{rabbitmq_server_bin} stop')
    Execute(daemon_cmd, 
            environment={'HOME': params.rabbitmq_server_home}
    )

  def status(self, env):
    import params
    env.set_params(params)
    check_process_status(params.rabbitmq_server_pid_file)

if __name__ == "__main__":
  RabbitMQServer().execute()
