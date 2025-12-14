<template>
	<div class="p-6 space-y-4">
		<h2 class="text-xl font-semibold">{{ t('home.title') }}</h2>
		<p class="text-sm text-gray-500">
			{{ t('home.subtitle') }}
		</p>
		<el-card shadow="hover">
			<div class="flex items-center space-x-3">
				<el-avatar :size="46" :src="userInfo?.photo">
					{{ userInfo?.userName?.[0] || 'U' }}
				</el-avatar>
				<div>
					<div class="text-lg font-medium">{{ userInfo?.userName || t('home.welcome') }}</div>
					<div class="text-xs text-gray-500">{{ t('home.tenant') }}：{{ userInfo?.tenantName || '-' }}</div>
				</div>
			</div>
		</el-card>
	</div>
</template>

<script setup lang="ts" name="dashboard">
import { useUserInfo } from '/@/stores/userInfo';
import { computed } from 'vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n();
const userStore = useUserInfo();
const userInfo = computed(() => userStore.userInfos?.user);
</script>
