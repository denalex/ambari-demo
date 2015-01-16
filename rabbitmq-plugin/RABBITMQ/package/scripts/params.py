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

Ambari Agent

"""

from resource_management.libraries.functions.version import format_hdp_stack_version, compare_versions
from resource_management import *

config = Script.get_config()

rabbitmq_server_bin = '/etc/init.d/rabbitmq-server'
rabbitmq_server_pid_file = '/var/run/rabbitmq/pid'
rabbitmq_server_user = 'rabbitmq'
rabbitmq_server_home = '/var/lib/rabbitmq'

smokeuser = config['configurations']['cluster-env']['smokeuser']
